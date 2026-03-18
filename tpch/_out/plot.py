import csv
from pathlib import Path
import matplotlib.pyplot as plt

BASE_DIR = Path(__file__).resolve().parent

# ---- Creation benchmark plot ----
CREATION_CSVS = [
    BASE_DIR / "creation_benchmark_sf1.csv",
    BASE_DIR / "creation_benchmark_sf10.csv",
    BASE_DIR / "creation_benchmark_sf30.csv",
]

SF_ORDER = [1, 10, 30]
FORMATS = ["hive", "delta", "iceberg"]
COLORS = {
    "hive": "#2A9D8F",     # teal
    "delta": "#E76F51",    # coral
    "iceberg": "#8E44AD",  # purple
}
MARKERS = {
    "hive": "o",
    "delta": "s",
    "iceberg": "^",
}

# Load creation data
creation = {fmt: {} for fmt in FORMATS}
for path in CREATION_CSVS:
    if not path.exists():
        raise FileNotFoundError(path)
    sf = int(path.stem.replace("creation_benchmark_sf", ""))
    with path.open(newline="") as f:
        rows = list(csv.DictReader(f))
    for row in rows:
        label = row["label"]
        fmt = label.split("_")[0]
        if fmt not in creation:
            continue
        mean = float(row["mean"])
        std = float(row["std"])
        creation[fmt][sf] = (mean, std)

plt.style.use("seaborn-v0_8-whitegrid")
fig, ax = plt.subplots(figsize=(8.2, 4.8))

for fmt in FORMATS:
    means = [creation[fmt][sf][0] for sf in SF_ORDER]
    stds = [creation[fmt][sf][1] for sf in SF_ORDER]
    ax.errorbar(
        SF_ORDER,
        means,
        yerr=stds,
        marker=MARKERS[fmt],
        markersize=7,
        linewidth=2.2,
        capsize=5,
        color=COLORS[fmt],
        label=fmt.capitalize(),
    )
    ax.fill_between(
        SF_ORDER,
        [m - s for m, s in zip(means, stds)],
        [m + s for m, s in zip(means, stds)],
        color=COLORS[fmt],
        alpha=0.12,
    )

ax.set_title("TPC-H Collection Creation Time", pad=10)
ax.set_xlabel("Database Size (GB)")
ax.set_ylabel("Time (seconds)", rotation=0, ha="left", va="bottom")
ax.yaxis.set_label_coords(-0.02, 1.02)
ax.set_xticks(SF_ORDER)
ax.set_xticklabels([str(sf) for sf in SF_ORDER])
ax.grid(True, axis="y", alpha=0.3)
ax.grid(False, axis="x")
ax.legend(title="Format", frameon=False, loc="upper left")

out_path = BASE_DIR / "creation_benchmark_plot.png"
fig.tight_layout()
fig.savefig(out_path, dpi=160)
print(f"Saved plot to {out_path}")

# ---- Q1 benchmark plot ----
Q1_CSV = BASE_DIR / "q1_benchmark_sf1.csv"
if not Q1_CSV.exists():
    raise FileNotFoundError(Q1_CSV)

q1_rows = {}
with Q1_CSV.open(newline="") as f:
    for row in csv.DictReader(f):
        label = row["label"]
        fmt = label.split("_")[0]
        mean = float(row["mean"])
        std = float(row["std"])
        q1_rows[fmt] = (mean, std)

y_max = max((m + s for m, s in q1_rows.values()), default=1.0)
plot_top = y_max * 2.0 if y_max > 0 else 1.0

fig, ax = plt.subplots(figsize=(7.6, 4.8))

# SF1 data points (slight x-offsets to avoid overlap)
offsets = {
    "hive": 0.20,
    "delta": 0.35,
    "iceberg": 0.50,
}
for fmt in FORMATS:
    if fmt in q1_rows:
        mean, std = q1_rows[fmt]
        x = 1 + offsets.get(fmt, 0.0)
        ax.errorbar(
            [x],
            [mean],
            yerr=[std],
            marker=MARKERS[fmt],
            markersize=8,
            linewidth=2.0,
            elinewidth=1.6,
            capsize=4,
            color=COLORS[fmt],
            label=fmt.capitalize(),
        )
        ax.annotate(
            f"{mean:.1f}s",
            (x, mean),
            textcoords="offset points",
            xytext=(0, 8),
            ha="center",
            fontsize=9,
            color=COLORS[fmt],
        )

# OOM markers: place just below top, add arrows
oom_y = plot_top * 0.5
for sf, xoff in [(10, 0.0), (30, -0.25)]:
    x = sf + xoff
    ax.scatter([x], [oom_y], s=80, marker="x", color="#444444")
    ax.annotate(
        "Not enough\nmemory",
        (x, oom_y),
        textcoords="offset points",
        xytext=(0, 10),
        ha="center",
        va="bottom",
        fontsize=9,
        color="#444444",
    )

ax.set_title("TPC-H Q1 Time", pad=10)
ax.set_xlabel("Database Size (GB)")
ax.set_ylabel("Time (seconds)", rotation=0, ha="left", va="bottom")
ax.yaxis.set_label_coords(-0.02, 1.02)
ax.set_xticks(SF_ORDER)
ax.set_xticklabels([str(sf) for sf in SF_ORDER])
ax.set_xlim(0, 31.5)
ax.set_ylim(0, plot_top)

ax.grid(True, axis="y", alpha=0.3)
ax.grid(False, axis="x")
ax.legend(title="Format", frameon=False, loc="upper left")

out_path = BASE_DIR / "q1_benchmark_plot.png"
fig.tight_layout()
fig.savefig(out_path, dpi=160)
print(f"Saved plot to {out_path}")

# ---- Q1 benchmark plot (SF1 only, no OOM markers) ----
fig, ax = plt.subplots(figsize=(6.8, 4.6))

sf1_offsets = {
    "hive": -0.08,
    "delta": 0.0,
    "iceberg": 0.08,
}
for fmt in FORMATS:
    if fmt in q1_rows:
        mean, std = q1_rows[fmt]
        x = 1 + sf1_offsets.get(fmt, 0.0)
        ax.errorbar(
            [x],
            [mean],
            yerr=[std],
            marker=MARKERS[fmt],
            markersize=8,
            linewidth=2.0,
            elinewidth=1.6,
            capsize=4,
            color=COLORS[fmt],
            label=fmt.capitalize(),
        )
        ax.annotate(
            f"{mean:.1f}s",
            (x, mean),
            textcoords="offset points",
            xytext=(0, 8),
            ha="center",
            fontsize=9,
            color=COLORS[fmt],
        )

ax.set_title("TPC-H Q1 Time (SF1 only)", pad=10)
ax.set_xlabel("Database Size (GB)")
ax.set_ylabel("Time (seconds)", rotation=0, ha="left", va="bottom")
ax.yaxis.set_label_coords(-0.02, 1.02)
ax.set_xticks([1])
ax.set_xticklabels(["1"])
ax.set_xlim(0.5, 1.5)
ax.set_ylim(0, plot_top)

ax.grid(True, axis="y", alpha=0.3)
ax.grid(False, axis="x")
ax.legend(title="Format", frameon=False, loc="upper left")

out_path = BASE_DIR / "q1_benchmark_plot_sf1.png"
fig.tight_layout()
fig.savefig(out_path, dpi=160)
print(f"Saved plot to {out_path}")
