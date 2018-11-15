import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amy_Pc
 */

public class Autocomplete
{
    
    private class Node {
        String prefix;
        HashMap<Character, Node> children;
        // Does this node represent the last character in a word?
        boolean isWord;

        private Node(String prefix) {
            this.prefix = prefix;
            this.children = new HashMap<>();
        }
    }

    // The trie
    private Node trie;

    // Construct the trie from the dictionary
    public Autocomplete(ArrayList<String> dict) {
        trie = new Node("");
        for (String s : dict) 
            insertWord(s);
    }

    // Insert a word into the trie
    void insertWord(String s) {
        // Iterate through each character in the string. If the character is not
        // already in the trie then add it
        Node curr = trie;
        for (int i = 0; i < s.length(); i++) {
            if (!curr.children.containsKey(s.charAt(i))) {
                curr.children.put(s.charAt(i), new Node(s.substring(0, i + 1)));
            }
            curr = curr.children.get(s.charAt(i));
            if (i == s.length() - 1) curr.isWord = true;
        }
    }

    // Find all words in trie that start with prefix
    public List<String> getWordsForPrefix(String pre) {
        List<String> results = new LinkedList<String>();

        // Iterate to the end of the prefix
        Node curr = trie;
        for (char c : pre.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return results;
            }
        }

        // At the end of the prefix, find all child words
        findAllChildWords(curr, results);
        return results;
    }
    
    
    public Boolean search(String pre) 
    {
        Node curr = trie;
        for (char c : pre.toCharArray()) 
        {
            if (curr.children.containsKey(c)) 
            {
                curr = curr.children.get(c);
            } 
            else 
            {
                return false;
            }
        }

        
        
        return curr.isWord;
    }

    // Recursively find every child word
    private void findAllChildWords(Node n, List<String> results) {
        if (n.isWord) results.add(n.prefix);
        for (Character c : n.children.keySet()) {
            findAllChildWords(n.children.get(c), results);
        }
    }
    
    public static void main(String args[]) throws IOException  
    {
        
        //String[] dict = {"Hello", "World","am","amit", "amitgupta"}; 
        //Autocomplete txt=new Autocomplete(dict);
        //List<String> results = new LinkedList<String>();
        //results = txt.getWordsForPrefix("am");
        //for(String res : results){
           // System.out.println(res);
       // }
        
    ///temp comment till line 127    
       /* String line;
        ArrayList<String> str = new ArrayList<String>();
        try (
                InputStream fis = new FileInputStream("amy.txt");
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);
            ) 
            {
                while ((line = br.readLine()) != null) 
                {
                    // Do your thing with line
                    //System.out.println(line); 
                    str.add(line);
                    //String[] words = line.split(" ");
                    //System.out.println(line); 
                    //insert(words[0]);
                    /*insert(words[1]);
                    insert(words[2]);*/
                    //dict.p
        /*        }
                Autocomplete txt=new Autocomplete(str);
                List<String> results = new LinkedList<String>();
                results = txt.getWordsForPrefix("accept");
                for(String res : results){
                    System.out.println(res);
                } 
            }*/
    }
}