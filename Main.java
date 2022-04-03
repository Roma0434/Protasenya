package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', ',', '.', ' ',
            '-', '"', '\n', '\t', '–', ':', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public static void main(String[] args) throws IOException {
        new FileInputStream("src\\com\\company\\Original.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Шифруем текст по ключу.\n" +
                "2 - Расшифровываем текст по ключу. \n" +
                "3 -Расшифровка текста с помощью brute force ");
        int number = scanner.nextInt();

        String decrypt;
        if (number == 1) {
            System.out.println("Введите ключ:");
            int key = scanner.nextInt();
            FileOutputStream fileOutputStream = new FileOutputStream("src\\com\\company\\textEncrypt.txt", true);
            new FileInputStream("src\\com\\company\\textEncrypt.txt");
            decrypt = new String(Files.readAllBytes(Paths.get("src\\com\\company\\Original.txt")));
            String encrypted = encrypt(decrypt, key, alphabet);
            byte[] buffer = encrypted.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);

        } else if (number == 2) {
            System.out.println("Введите ключ:");
            int key = scanner.nextInt();
            String reading = new String(Files.readAllBytes(Paths.get("src\\com\\company\\textEncrypt.txt")));
            FileOutputStream fileDecr = new FileOutputStream("src\\com\\company\\textDecrypt.txt");
            decrypt = decrypt(reading, key, alphabet);
            byte[] buff = decrypt.getBytes();
            fileDecr.write(buff, 0, buff.length);

        } else if (number == 3) {
            String text = new String(Files.readAllBytes(Paths.get("src\\com\\company\\Original.txt")));
            bryteForce(text);

        }

    }

    private static String encrypt(String text, int key, char[] alphabet) {
        String result = "";
        char[] array = text.toCharArray();
        int index = 0;

        for (int i = 0; i < text.length(); ++i) {
            char letter = array[i];

            int j;
            for (j = 0; j < alphabet.length; ++j) {
                if (letter == alphabet[j]) {
                    index = j;
                }
            }

            j = (alphabet.length + index + key) % alphabet.length;
            char encryptedLetter = alphabet[j];
            result = result + encryptedLetter;
        }

        return result;
    }

    private static String decrypt(String reading, int key, char[] alphabet) {
        String result = "";
        char[] array = reading.toCharArray();
        int index = 0;

        for (int i = 0; i < array.length; ++i) {
            char letter = array[i];

            int j;
            for (j = 0; j < alphabet.length; ++j) {
                if (letter == alphabet[j]) {
                    index = j;
                }
            }

            j = (alphabet.length + index - key) % alphabet.length;
            char decryptedLetter = alphabet[j];
            result = result + decryptedLetter;
        }

        return result;
    }

    private static String bryteForce(String text) {

        char [] passArr = text.toCharArray();

        String brutePass = "";
        boolean solved = false;
        StringBuilder stringBuilder = new StringBuilder(brutePass);
        while (solved != true) {
            for (int j = 0; j < passArr.length; j++) {
                for (int i = 0; i < Main.alphabet.length; i++) {

                    if (Main.alphabet[i] == passArr[j]) {
                        stringBuilder.append(passArr[j]);
                        System.out.println(stringBuilder.toString());
                        solved = true;
                    }
                }
            }
        }
        return text;
    }
}


