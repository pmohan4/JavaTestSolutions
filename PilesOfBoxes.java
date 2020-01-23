package com.matillion;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.Collectors;


class Result {

    /*
     * Complete the 'pilesOfBoxes' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY boxesInPiles as parameter.
     */

    public static long pilesOfBoxes(List<Integer> boxesInPiles) {
        // Write your code here
        long count = 0L;
        long prevPileSize = 0L;
        long prevNumberOfStack =  0L;

        Map<Integer, Long> pileGroup = new TreeMap<>(Collections.reverseOrder());
        pileGroup.putAll(boxesInPiles.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        for(Map.Entry<Integer,Long> entry: pileGroup.entrySet()){
            if(prevPileSize == 0){
                prevPileSize = entry.getKey();
                prevNumberOfStack = entry.getValue();
            } else {
                count += ((prevPileSize - entry.getKey()) * prevNumberOfStack);
                prevPileSize = entry.getKey();
                prevNumberOfStack = entry.getValue();
            }
        }
        return count;
    }

}
public class PilesOfBoxes {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));
        int boxesInPilesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> boxesInPiles = new ArrayList<>();

        for (int i = 0; i < boxesInPilesCount; i++) {
            int boxesInPilesItem = Integer.parseInt(bufferedReader.readLine().trim());
            boxesInPiles.add(boxesInPilesItem);
        }

        long result = Result.pilesOfBoxes(boxesInPiles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

