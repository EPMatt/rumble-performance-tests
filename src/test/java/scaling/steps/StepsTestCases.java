package scaling.steps;

import helper.TestCase;
import java.util.List;

public class StepsTestCases {
    public static TestCase steps_protocols_1r = new TestCase(
            "steps_protocols_1",
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            ),
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps_protocols_1s = new TestCase(
            "steps_protocols_1",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols1k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols2k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols4k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols8k\")/TEI/text/body/p[2]",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols16k\")/TEI/text/body/p[2]"
            )
    );

    public static TestCase steps_protocols_2r = new TestCase(
            "steps_protocols_2",
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols2k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols4k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols8k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols16k/*.xml\")/descendant::date"
            ),
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols2k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols4k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols8k/*.xml\")/descendant::date",
                "xml-files(\"./data/protocols16k/*.xml\")/descendant::date"
            )
    );

    public static TestCase steps_protocols_2s = new TestCase(
            "steps_protocols_2",
            List.of(
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols1k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols2k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols4k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols8k\")/descendant::date",
                "declare default element namespace \"http://www.tei-c.org/ns/1.0\"; collection(\"data/protocols16k\")/descendant::date"
            )
    );

    public static TestCase steps_edgar1r = new TestCase(
            "steps_edgar1",
            List.of(
                "xml-files(\"./data/edgar16/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar64/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar256/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar1024/*.xml\")/xbrl/context/entity/identifier"
            ),
            List.of(
                "xml-files(\"./data/edgar16/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar64/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar256/*.xml\")/xbrl/context/entity/identifier",
                "xml-files(\"./data/edgar1024/*.xml\")/xbrl/context/entity/identifier"
            )
    );

    public static TestCase steps_edgar1s = new TestCase(
            "steps_edgar1",
            List.of(
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar16\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar64\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar256\")/xbrl/context/entity/identifier",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar1024\")/xbrl/context/entity/identifier"
            )
    );

    public static TestCase steps_edgar2r = new TestCase(
            "steps_edgar2",
            List.of(
                "xml-files(\"./data/edgar16/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar64/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar256/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar1024/*.xml\")/descendant::segment"
            ),
            List.of(
                "xml-files(\"./data/edgar16/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar64/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar256/*.xml\")/descendant::segment",
                "xml-files(\"./data/edgar1024/*.xml\")/descendant::segment"
            )
    );

    public static TestCase steps_edgar2s = new TestCase(
            "steps_edgar2",
            List.of(
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar16\")/descendant::segment",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar64\")/descendant::segment",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar256\")/descendant::segment",
                "declare default element namespace \"http://www.xbrl.org/2003/instance\"; collection(\"./data/edgar1024\")/descendant::segment"
            )
    );

    public static TestCase steps_rumble_improvements = new TestCase(
            "steps_improvements",
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            ),
            List.of(
                "xml-files(\"./data/protocols1k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols2k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols4k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols8k/*.xml\")/TEI/text/body/p[2]",
                "xml-files(\"./data/protocols16k/*.xml\")/TEI/text/body/p[2]"
            )
    );
    public static List<TestCase> allRumbleCases = List.of(
        steps_edgar1r,
        steps_edgar2r,
        steps_protocols_1r,
        steps_protocols_2r
    );
    public static List<TestCase> allRumbleNoEdgar = List.of(
        steps_protocols_1r,
        steps_protocols_2r
    );
    public static List<TestCase> allSaxonCases = List.of(
        steps_edgar1s,
        steps_edgar2s,
        steps_protocols_1s,
        steps_protocols_2s
    );
    public static List<TestCase> stepImprovementCases = List.of(steps_rumble_improvements);
}
