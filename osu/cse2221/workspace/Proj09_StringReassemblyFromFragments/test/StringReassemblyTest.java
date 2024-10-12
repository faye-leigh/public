import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombination() {
//        fail("Not yet implemented");
        String s1 = "abc def";
        String s2 = " def ghi";
        final int overlap = 4;
        String c = StringReassembly.combination(s1, s2, overlap);
        String cExpected = "abc def ghi";
        assertEquals(cExpected, c);
    }

    @Test
    public void testAddToSetAvoidingSubstrings() {
//        fail("Not yet implemented");
        Set<String> s = new Set1L<String>();
        s.add("abc");
        s.add("bcd");
        Set<String> sExpected = s.newInstance();
        sExpected.add("abc");
        sExpected.add("bcd");
        sExpected.add("cde");
        StringReassembly.addToSetAvoidingSubstrings(s, "cde");
        assertEquals(sExpected, s);
    }

    @Test
    public void testAddToSetAvoidingSubstringsHasSubstring() {
//        fail("Not yet implemented");
        Set<String> s = new Set1L<String>();
        s.add("abc");
        s.add("cd");
        Set<String> sExpected = s.newInstance();
        sExpected.add("abc");
        sExpected.add("cde");
        StringReassembly.addToSetAvoidingSubstrings(s, "cde");
        assertEquals(sExpected, s);
    }

    @Test
    public void testLinesFromInput() {
//        fail("Not yet implemented");
        SimpleReader in = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> lines = new Set1L<String>();
        Set<String> linesExpected = lines.newInstance();
        linesExpected.add("Bucks -- Beat");
        linesExpected.add("Go Bucks");
        linesExpected.add("o Bucks -- B");
        linesExpected.add("Beat Mich");
        linesExpected.add("Michigan~");
        lines = StringReassembly.linesFromInput(in);
        assertEquals(linesExpected, lines);
    }

    @Test
    public void testPrintWithLineSeparators() {
//        fail("Not yet implemented");
        SimpleWriter outFile = new SimpleWriter1L("test/test.txt");
        String s = "Michigan~";
        StringReassembly.printWithLineSeparators(s, outFile);
        outFile.close();
        SimpleReader out = new SimpleReader1L("test/test.txt");
        SimpleReader outExpected = new SimpleReader1L("test/testExpected.txt");
        while (!outExpected.atEOS() && !out.atEOS()) {
            assertEquals(outExpected.nextLine(), out.nextLine());
        }
        out.close();
        outExpected.close();
    }

}
