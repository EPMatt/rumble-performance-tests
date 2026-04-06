#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"
URL_LIST_FILE="${ROOT_DIR}/edgars_to_download.md"
OUTPUT_DIR="${SCRIPT_DIR}/edgar16"
EXPECTED_COUNT=16

mkdir -p "${OUTPUT_DIR}"

if [[ ! -f "${URL_LIST_FILE}" ]]; then
  echo "URL list file not found: ${URL_LIST_FILE}" >&2
  exit 1
fi

downloaded=0
failed=0

while IFS= read -r url || [[ -n "${url}" ]]; do
  # Skip blank lines and comments.
  [[ -z "${url}" || "${url}" =~ ^# ]] && continue

  filename="$(basename "${url}")"
  target="${OUTPUT_DIR}/${filename}"

  echo "Downloading ${filename}..."
  if curl --location --fail --silent --show-error --compressed \
      --retry 3 --retry-delay 2 \
      --header "User-Agent: ExampleCorp example@example.com" \
      --header "Host: www.sec.gov" \
      --header "Referer: https://www.sec.gov/" \
      "${url}" \
      --output "${target}"; then
    downloaded=$((downloaded + 1))
  else
    echo "Failed: ${url}" >&2
    failed=$((failed + 1))
  fi
done < "${URL_LIST_FILE}"

shopt -s nullglob
xml_files=("${OUTPUT_DIR}"/*.xml)
actual_count="${#xml_files[@]}"

echo "Downloaded this run: ${downloaded}"
echo "Failed this run: ${failed}"
echo "Files currently in ${OUTPUT_DIR}: ${actual_count}"

if [[ "${actual_count}" -ne "${EXPECTED_COUNT}" ]]; then
  echo "Expected ${EXPECTED_COUNT} files in ${OUTPUT_DIR}, found ${actual_count}." >&2
  exit 1
fi

echo "Success: all ${EXPECTED_COUNT} Edgar XML files are present in ${OUTPUT_DIR}."
