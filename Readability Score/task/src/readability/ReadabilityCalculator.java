package readability;

import java.util.Scanner;

public class ReadabilityCalculator {
    private double wordCount = 0;
    private double sentenceCount = 0;
    private double characterCount = 0;
    private double syllableCount = 0;
    private double polySyllableCount = 0;
    private double ari; // Automated Readability Index
    private double fk; // Flesch-Kincaid readability tests
    private double smog; // Simple Measure of Gobbledygook
    private double cl; // Coleman-Liau Index


    public ReadabilityCalculator(Scanner scanner) {
        while (scanner.hasNext()) {
            String word = scanner.next();
            wordCount++;
            characterCount += word.length();
            if (word.matches("\\w+[.?!]") || !scanner.hasNext()) {
                sentenceCount++;
            }
            int syllables = countSyllables(word);
            syllableCount += syllables;
            if (syllables > 2) {
                polySyllableCount++;
            }
        }

        ari = calculateAutomatedReadabilityIndex();
        fk = calculateFleschKincaidReadabilityTests();
        smog = calculateSimpleMeasureOfGobbledygook();
        cl = calculateColemanLiauIndex();
    }

    private int countSyllables(String word) {
        int syllables = 0;
        word = word.replaceAll("\\W", "").toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (isVowel(letter)) {
                if (i > 0) {
                    if (!isVowel(word.charAt(i - 1)) && !(letter == 'e' && i == word.length() - 1)) {
                        syllables++;
                    }
                }
                else {
                    syllables++;
                }
            }
        }

        return syllables == 0 ? 1 : syllables;
    }

    private boolean isVowel(char letter) {
        return letter == 'a' ||letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'y';
    }

    public void printStats() {
        System.out.printf("Words: %.0f\nSentences: %.0f\nCharacters: %.0f\nSyllables: %.0f\nPolysyllables: %.0f\n", wordCount, sentenceCount, characterCount, syllableCount, polySyllableCount);
    }

    private double calculateAutomatedReadabilityIndex() {
        return 4.71 * (characterCount/wordCount) + 0.5 * (wordCount/sentenceCount) - 21.43;
    }


    private double calculateFleschKincaidReadabilityTests() {
        return 0.39 * (wordCount / sentenceCount) + 11.8 * (syllableCount / wordCount) - 15.59;
    }

    private double calculateSimpleMeasureOfGobbledygook() {
        return 1.043 * Math.sqrt(polySyllableCount * (30 / sentenceCount)) + 3.1291;
    }

    private double calculateColemanLiauIndex() {
        double L = (characterCount / wordCount) * 100;
        double S = (sentenceCount / wordCount) * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    public void printARI() {
        System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", ari, getReaderAge(ari));
    }

    public void printFK() {
        System.out.printf("Flesch-Kincaid readability tests: %.2f (about %d year olds).\n", fk, getReaderAge(fk));
    }

    public void printSMOG() {
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", smog, getReaderAge(smog));
    }

    public void printCL() {
        System.out.printf("Coleman-Liau index: %.2f (about %d year olds).\n", cl, getReaderAge(cl));
    }

    public void printAll() {
        printARI();
        printFK();
        printSMOG();
        printCL();

        double averageAge = (double) (getReaderAge(ari) + getReaderAge(fk) + getReaderAge(smog) + getReaderAge(cl)) / 4.0;
        System.out.printf("\nThis text should be understood in average by %.2f year olds.", averageAge);
    }

    private int getReaderAge(double score) {
        switch ((int) Math.ceil(score)) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
            case 14:
            default:
                return 24;
        }
    }
}
