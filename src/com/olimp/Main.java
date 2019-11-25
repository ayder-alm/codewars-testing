package com.olimp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * For a given chemical formula represented by a string, count the number of atoms of each element contained in the
 * molecule and return an object (associative array in PHP, Dictionary<string, int> in C#, Map<String,Integer> in Java).
 * <p>
 * For example:
 * <p>
 * String water = "H2O";
 * parseMolecule.getAtoms(water); // return [H: 2, O: 1]
 * <p>
 * String magnesiumHydroxide = "Mg(OH)2";
 * parseMolecule.getAtoms(magnesiumHydroxide); // return ["Mg": 1, "O": 2, "H": 2]
 * <p>
 * String fremySalt = "K4[ON(SO3)2]2";
 * parseMolecule.getAtoms(fremySalt); // return ["K": 4, "O": 14, "N": 2, "S": 4]
 * <p>
 * parseMolecule.getAtoms("pie"); // throw an IllegalArgumentException
 * As you can see, some formulas have brackets in them. The index outside the brackets tells you that you have to
 * multiply count of each atom inside the bracket on this index. For example, in Fe(NO3)2 you have one iron atom, two
 * nitrogen atoms and six oxygen atoms.
 * <p>
 * Note that brackets may be round, square or curly and can also be nested. Index after the braces is optional.
 */

public class Main {

    public static void main(String[] args) {
        String testCase = "K4[ON(SO3)2]2";
        System.out.println(testCase);
        Map<String, Integer> result = ParseMolecule.getAtoms(testCase);
        System.out.println(result);
    }


    public static class ParseMolecule {

        public static Map<String, Integer> getAtoms(String formula) {
            //remove illegal atom names etc
            if (formula.length() < 1
                    || formula.matches("(.*[a-z]{3,}.*)|(^[\\da-z].+)|(.*[^A-Za-z()\\[]\\{}\\d].*)")
                    || !isBalancedParantheses(formula)) {
                throw new IllegalArgumentException();
            }
            HashMap<String, Integer> result = new HashMap<>();
            //replace different types of brackets by "()" because we know they're correctly mirrored
            //we will now have ! for open bracket and # for close, because they're easier to use in regexp
            formula = formula.replaceAll("\\s", "")
                    .replaceAll("[{\\[(]", "!")
                    .replaceAll("[}\\])]", "#");

            //next we remove all brackets containing items
            Pattern pMolecule = Pattern.compile("!\\w+?#");
            Pattern pNumberedMolecule = Pattern.compile("!\\w+?#\\d*");
            Set<String> molecules;
            do {
                molecules = new HashSet<>();
                Matcher mNumberedMolecule = pNumberedMolecule.matcher(formula);
                while (mNumberedMolecule.find()) {
                    String moleculeX = mNumberedMolecule.group();
                    molecules.add(moleculeX);
                }
                for (String nMolecule : molecules) {
                    String sMolecule;
                    StringBuilder nMoleculeUnwinded;
                    Matcher mMolecule = pMolecule.matcher(nMolecule);
                    if (mMolecule.find()) {
                        sMolecule = mMolecule.group();
                        String moleculeCountStr = nMolecule.replaceAll(sMolecule, "");
                        if (!moleculeCountStr.isEmpty()) {
                            int count = Integer.parseInt(moleculeCountStr);
                            nMoleculeUnwinded = new StringBuilder(sMolecule.length() * count);
                            sMolecule = sMolecule.replaceAll("[!#]", "");
                            for (int i = 0; i < count; i++) {
                                nMoleculeUnwinded.append(sMolecule);
                            }
                            formula = formula.replaceAll(nMolecule, nMoleculeUnwinded.toString());
                        } else {
                            String nMoleculeNoBrackets = nMolecule.substring(1, nMolecule.length() - 1);
                            formula = formula.replaceAll(nMolecule, nMoleculeNoBrackets);
                        }

                    }
                }
            } while (!molecules.isEmpty());

            //next we remove remaining atoms containing numbers
            Pattern numberedAtomPattern = Pattern.compile("[A-Z][a-z]{0,2}\\d+");
            Matcher numberedAtomMatcher = numberedAtomPattern.matcher(formula);
            Set<String> numberedAtoms = new HashSet<>();
            while (numberedAtomMatcher.find()) {
                numberedAtoms.add(numberedAtomMatcher.group());
            }
            for (String numberedAtom : numberedAtoms) {
                String number = numberedAtom.replaceAll("\\D+", "");
                String atom = numberedAtom.replace(number, "");
                StringBuilder atoms = new StringBuilder();
                int nAtoms = Integer.parseInt(number);
                for (int i = 0; i < nAtoms; i++) {
                    atoms.append(atom);
                }
                formula = formula.replaceAll(numberedAtom, atoms.toString());
            }

            //next we count atoms in resulting unwinded formula that consists only of atoms(no numbers or brackets)
            Pattern atomPattern = Pattern.compile("[A-Z][a-z]{0,2}");
            Matcher matcher = atomPattern.matcher(formula);
            while (matcher.find()) {
                String atom = matcher.group();
                result.merge(atom, 1, Integer::sum);
            }
            return result;
        }

        private static boolean isBalancedParantheses(String formula) {
            Deque<Character> deque = new ArrayDeque<>();
            for (Character ch : formula.toCharArray()) {
                switch (ch) {
                    case '(':
                    case '[':
                    case '{':
                        deque.push(ch);
                        break;
                    case ')':
                        if (deque.peek() != null && deque.peek() == '(') {
                            deque.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (deque.peek() != null && deque.peek() == '[') {
                            deque.pop();
                        } else {
                            return false;
                        }
                        break;
                    case '}':
                        if (deque.peek() != null && deque.peek() == '{') {
                            deque.pop();
                        } else {
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
            return deque.isEmpty();
        }
    }

}
