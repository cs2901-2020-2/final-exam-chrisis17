package cslab;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Test
public class BlockudokuTest {
    public void testCase0() throws IOException {
        generateBoardTest(0);
    }

    public List<String> readFile(int testNumber, String type){
        String fileName = "test_case<testNumber>_<type>";
        fileName = fileName.replace("<testNumber>", Integer.toString(testNumber));
        fileName = fileName.replace("<type>", type);
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scan = new Scanner(is);
        List<String> lines = new ArrayList<String>();
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            lines.add(line);
        }
        return lines;
    }

    private int readInput(int testNumber){
        List<String> lines = readFile(testNumber, "input");
        int n = Integer.parseInt(lines.get(0));
        return n;
    }

    private List<String> readOutput(int testNumber){
        List<String> lines = readFile(testNumber, "output");
        return lines;
    }

    private void generateBoardTest(int i) throws IOException {
        List<String> expectedOutput = readOutput(i);
        int[][] board = Blockudoku.generateBoard();
        List<String> output = new ArrayList<String>();
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                output.add(String.valueOf(board[row][column]));
            }
        }
        Assert.assertEquals(output, expectedOutput);
    }


}
