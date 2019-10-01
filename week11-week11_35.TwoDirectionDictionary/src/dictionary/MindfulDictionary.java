package dictionary;

/**
 *
 * @author thesh
 */
import java.util.*;
import java.io.*;

public class MindfulDictionary {

    private Map<String, String> dictionary = this.dictionary = new HashMap<String, String>();
    private String file;

    public MindfulDictionary() {

    }

    public MindfulDictionary(String file) {
        this.file = file;
    }

    // METHOD. Loads file whose name is given in parameter constructor,
    // returns true if it can read the file, else returns false;
    public boolean load() {
        try {
            File f = new File(this.file);
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                this.dictionary.put(parts[0], parts[1]);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save() {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (String key : this.dictionary.keySet()) {
                String word = key;
                String translation = this.dictionary.get(key);
                String entry = word + ":" + translation + "\n";
                writer.write(entry);
            }
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // METHOD. adds a word to the dictionary. Each word has only one translation
    // if the same word is added twice, nothing happens.
    public void add(String word, String translation) throws Exception {
        if (!this.dictionary.containsKey(word)) {
            this.dictionary.put(word, translation);
        }
    }

    // METHOD. Returns the word translation; if the word isn't recognized, it
    // returns null
    public String translate(String word) {
        // searches keys first for word
        if (this.dictionary.containsKey(word)) {
            return this.dictionary.get(word);
        } // searches values second for word
        else if (!this.dictionary.containsKey(word)) {
            for (String k : this.dictionary.keySet()) {
                if (this.dictionary.get(k).equals(word)) {
                    return k;
                }
            }
        }
        // nothing found
        return null;
    }

    // METHOD. Removes the given word and its translation from your dictionary
    public void remove(String word) {
        List<String> toBeRemoved = new ArrayList<String>(); // list of keys to be removed from dictionary
        // searches keys first for word
        if (this.dictionary.containsKey(word)) {
            this.dictionary.remove(word);
            // searches values second for word
        } else if (!this.dictionary.containsKey(word)) {
            for (String k : this.dictionary.keySet()) {
                if (this.dictionary.get(k).equals(word)) {
                    toBeRemoved.add(k);
                }
            }
        }
        for (String key : toBeRemoved) {
            this.dictionary.remove(key);
        }
    }

}
