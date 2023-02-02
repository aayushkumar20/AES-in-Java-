import java.util.*;
import java.lang.*;
import java.math.*;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

// Write a program to ask user to enter a string and pass it to encrypt function.
class input
{
    public static void enter(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string to encrypt: ");
        String str = sc.nextLine();
        encrypt(str);
    }
    // Write a program to encrypt the string using the following algorithm.
    // AES encryption algorithm
    public static void encrypt(String str)
    {
        try
        {
            String key = "12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(str.getBytes());
            // send the encrypted string to the divide function
            divide(encrypted);
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
    }
    // Divide the encrypted string into 4 equal parts and pass it to the function.
    public static void divide(byte[] encrypted)
    {
        int len = encrypted.length;
        int n = len/4;
        byte[] part1 = new byte[n];
        byte[] part2 = new byte[n];
        byte[] part3 = new byte[n];
        byte[] part4 = new byte[n];
        for(int i=0; i<n; i++)
        {
            part1[i] = encrypted[i];
        }
        for(int i=n; i<2*n; i++)
        {
            part2[i-n] = encrypted[i];
        }
        for(int i=2*n; i<3*n; i++)
        {
            part3[i-2*n] = encrypted[i];
        }
        for(int i=3*n; i<4*n; i++)
        {
            part4[i-3*n] = encrypted[i];
        }
        // send the 4 parts to the function
        sort(part1, part2, part3, part4);
    }
    // Sort the 4 parts in ascending order and pass it to the function.
    public static void sort(byte[] part1, byte[] part2, byte[] part3, byte[] part4)
    {
        Arrays.sort(part1);
        Arrays.sort(part2);
        Arrays.sort(part3);
        Arrays.sort(part4);
        // send the sorted parts to the function
        merge(part1, part2, part3, part4);
    }
    // Merge the 4 parts and pass it to the function.
    public static void merge(byte[] part1, byte[] part2, byte[] part3, byte[] part4)
    {
        int len = part1.length;
        byte[] merged = new byte[4*len];
        for(int i=0; i<len; i++)
        {
            merged[i] = part1[i];
        }
        for(int i=len; i<2*len; i++)
        {
            merged[i] = part2[i-len];
        }
        for(int i=2*len; i<3*len; i++)
        {
            merged[i] = part3[i-2*len];
        }
        for(int i=3*len; i<4*len; i++)
        {
            merged[i] = part4[i-3*len];
        }
        // send the merged string to the function
        decrypt(merged);
    }
    // Decrypt the merged string using the following algorithm.
    // AES decryption algorithm
    public static void decrypt(byte[] merged)
    {
        try
        {
            String key = "12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypted = cipher.doFinal(merged);
            System.out.println("Decrypted string: " + new String(decrypted));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
    }
    public static void main(String[] args) {
        enter(args);
    }
}
