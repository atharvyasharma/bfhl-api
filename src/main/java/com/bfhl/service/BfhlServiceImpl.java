package com.bfhl.service;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link IBfhlService}.
 *
 * <p>Logic summary:
 * <ul>
 *   <li>Each token in {@code data} is classified as a pure number, a pure alphabet, or a special char.</li>
 *   <li>Numbers are checked for odd/even by their numeric value.</li>
 *   <li>Alphabets are uppercased.</li>
 *   <li>sum = arithmetic sum of all numeric tokens (supports multi-digit values).</li>
 *   <li>concat_string = alphabets in input order → reversed → alternating upper/lower.</li>
 * </ul>
 */
@Service
public class BfhlServiceImpl implements IBfhlService {

    // ── Hardcoded personal details ────────────────────────────────────────────
    private static final String USER_ID      = "atharvya_sharma_01062006";
    private static final String EMAIL        = "atharvyasharma230072@acropolis.in";
    private static final String ROLL_NUMBER  = "0827AL231037";
    // ─────────────────────────────────────────────────────────────────────────

    @Override
    public BfhlResponse processData(BfhlRequest request) {

        List<String> data = request.getData();
        if (data == null) {
            data = Collections.emptyList();
        }

        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialChars     = new ArrayList<>();
        long         numericalSum     = 0;

        for (String token : data) {
            if (token == null || token.isEmpty()) {
                // treat empty tokens as special characters
                specialChars.add(token);
                continue;
            }

            if (isNumber(token)) {
                long value = Long.parseLong(token);
                numericalSum += value;
                if (value % 2 == 0) {
                    evenNumbers.add(token);
                } else {
                    oddNumbers.add(token);
                }
            } else if (isAlphabet(token)) {
                // single alpha char – store uppercased
                alphabets.add(token.toUpperCase());
            } else {
                specialChars.add(token);
            }
        }

        // Build response
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);
        response.setSum(String.valueOf(numericalSum));
        response.setConcatString(buildConcatString(alphabets));

        return response;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Returns true if the token is a valid integer (may be multi-digit).
     */
    private boolean isNumber(String token) {
        try {
            Long.parseLong(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if the token is a single alphabetic character.
     * Multi-character strings that are not purely numeric are treated as special chars.
     */
    private boolean isAlphabet(String token) {
        return token.length() == 1 && Character.isLetter(token.charAt(0));
    }

    /**
     * Builds the concat_string:
     * <ol>
     *   <li>Take alphabets in the order they were extracted (already uppercased).</li>
     *   <li>Reverse the list.</li>
     *   <li>Apply alternating capitalisation: index 0 → upper, 1 → lower, 2 → upper, …</li>
     * </ol>
     *
     * Example: input ["A", "Y", "B"] → reversed → ["B", "Y", "A"] → "ByA"
     */
    private String buildConcatString(List<String> alphabets) {
        if (alphabets.isEmpty()) {
            return "";
        }

        // Reverse (non-destructive copy)
        List<String> reversed = new ArrayList<>(alphabets);
        Collections.reverse(reversed);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            char ch = reversed.get(i).charAt(0);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(ch));
            } else {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }
}
