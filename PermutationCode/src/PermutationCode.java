import tester.Tester;
import java.util.*;
//PermutationCode.java
//Dante LoPriore and Jake Simeone

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> empty = new ArrayList<Character>();
    ArrayList<Character> copyAlphabet = new ArrayList<Character>(this.alphabet);

    for (int i = this.alphabet.size(); i > 0; i = i - 1) {
      Character randomChar = copyAlphabet.get(rand.nextInt(copyAlphabet.size()));
      empty.add(randomChar);
      copyAlphabet.remove(randomChar);
    }
    return empty;
  }

  // produce an encoded String from the given String
  String encode(String source) {
    if (source.length() > 0) {
      return this.encodeLetter(source.substring(0, 1), 0)
          + this.encode(source.substring(1, source.length()));
    }
    return "";
  }

  //to find the other letter in the alphabet and index of individual letter in code
  String encodeLetter(String letter, int start) {
    if (alphabet.get(start).toString().equals(letter)) {
      return this.code.get(start).toString();
    }
    return this.encodeLetter(letter, start + 1);
  }

  // produce a decoded String from the given String
  String decode(String code) {
    if (code.length() > 0) {
      return this.decodeLetter(code.substring(0, 1), 0)
          + this.decode(code.substring(1, code.length()));
    }
    return "";
  }

  //to find the index of individual letter in code and the other letter in the alphabet.
  String decodeLetter(String letter, int start) {
    if (code.get(start).toString().equals(letter)) {
      return this.alphabet.get(start).toString();
    }
    return this.decodeLetter(letter, start + 1);
  }
}

//to represent the tests and examples in the PermutationCode.java
class ExamplesPermutationCode {

  //Examples of ArrayLists
  ArrayList<Character> reverseAlphabet = new ArrayList<Character>(
      Arrays.asList('z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k',
          'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'));

  ArrayList<Character> randomCharactersCode1 = new ArrayList<Character>(
      Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'p', 'o', 'n', 'm', 'l', 'k',
          'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'));

  ArrayList<Character> randomCharactersCode2 = new ArrayList<Character>(
      Arrays.asList('g', 'a', 'b', 'a', 'v', 'a', 'd', 'a', 'r', 'a', 'p', 'a', 'n', 'a', 'l', 'a',
          'j', 'a', 'b', 'a', 'c', 'a', 'g', 'a', 'b', 'a'));

  //Examples of PermutationCode
  PermutationCode permCode1 = new PermutationCode();
  PermutationCode permCode2 = new PermutationCode(this.reverseAlphabet);
  PermutationCode permCode3 = new PermutationCode(this.randomCharactersCode1);
  PermutationCode permCode4 = new PermutationCode(this.randomCharactersCode2);

  //tests for the encode method
  void testEncode(Tester t) {

    t.checkExpect(permCode2.encode("rabbit"), "izyyrg");
    t.checkExpect(permCode2.encode("files"), "urovh");
    t.checkExpect(permCode1.encode("sold"), permCode1.encode("sold"));
  }

  //tests for the encodeLetter method
  void testEncodeLetter(Tester t) {
    t.checkExpect(permCode2.encodeLetter("q", 0), "j");
    t.checkExpect(permCode2.encodeLetter("t", 0), "g");
    t.checkExpect(permCode2.encodeLetter("t", 1), "g");

  }

  //tests for the decode method
  void testDecode(Tester t) {
    t.checkExpect(permCode2.decode("hklro"), "spoil");
    t.checkExpect(permCode2.decode("hkirmt"), "spring");
    t.checkExpect(permCode2.decode("zmw"), "and");
  }

  //tests for the decodeLetter method
  void testDecodeLetter(Tester t) {
    t.checkExpect(permCode2.decodeLetter("q", 0), "j");
    t.checkExpect(permCode2.decodeLetter("a", 0), "z");
    t.checkExpect(permCode2.decodeLetter("s", 1), "h");

  }

  //tests for the InitEncoder method
  void testInitEncoder(Tester t) {
    PermutationCode random1 = new PermutationCode(new PermutationCode().initEncoder());
    PermutationCode random2 = new PermutationCode(this.permCode2.initEncoder());
    PermutationCode random3 = new PermutationCode(this.permCode3.initEncoder());
    t.checkExpect(random1.initEncoder().size(), 26);
    t.checkExpect(random2.initEncoder().size(), 26);
    t.checkExpect(random3.initEncoder().size(), 26);
    t.checkExpect(random1, random1);
    t.checkExpect(random2, random2);
    t.checkExpect(random3, random3);
  }
}
