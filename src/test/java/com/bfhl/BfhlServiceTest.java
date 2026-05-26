package com.bfhl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BfhlServiceImpl}.
 */
class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ── Example A: Normal mixed input ─────────────────────────────────────────
    @Test
    @DisplayName("Example A – mixed input with letters, numbers, and special chars")
    void testMixedInput() {
        BfhlRequest req = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertEquals(List.of("1"), res.getOddNumbers());
        assertEquals(List.of("334", "4"), res.getEvenNumbers());
        assertEquals(List.of("A", "R"), res.getAlphabets());
        assertEquals(List.of("$"), res.getSpecialCharacters());
        assertEquals("339", res.getSum());

        // alphabets in order = [A, R] → reversed = [R, A] → "Ra"
        assertEquals("Ra", res.getConcatString());
    }

    // ── Example B: Numbers only ───────────────────────────────────────────────
    @Test
    @DisplayName("Example B – numbers only")
    void testNumbersOnly() {
        BfhlRequest req = new BfhlRequest(Arrays.asList("2", "3", "11", "8"));
        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertEquals(List.of("3", "11"), res.getOddNumbers());
        assertEquals(List.of("2", "8"), res.getEvenNumbers());
        assertTrue(res.getAlphabets().isEmpty());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("24", res.getSum());
        assertEquals("", res.getConcatString());
    }

    // ── Example C: Multi-character strings ───────────────────────────────────
    @Test
    @DisplayName("Example C – multi-char strings treated as special characters")
    void testMultiCharInput() {
        // Multi-character non-numeric tokens go to special characters
        BfhlRequest req = new BfhlRequest(Arrays.asList("AB", "12", "3", "cd"));
        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        // "AB" and "cd" are multi-char non-numbers → special chars
        assertEquals(List.of("AB", "cd"), res.getSpecialCharacters());
        assertEquals(List.of("3"), res.getOddNumbers());
        assertEquals(List.of("12"), res.getEvenNumbers());
        assertTrue(res.getAlphabets().isEmpty());
        assertEquals("15", res.getSum());
        assertEquals("", res.getConcatString());
    }

    // ── Empty array input ─────────────────────────────────────────────────────
    @Test
    @DisplayName("Empty data array – all lists empty, sum = 0")
    void testEmptyInput() {
        BfhlRequest req = new BfhlRequest(Collections.emptyList());
        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertTrue(res.getAlphabets().isEmpty());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("", res.getConcatString());
    }

    // ── Alphabets only ────────────────────────────────────────────────────────
    @Test
    @DisplayName("Alphabets only – concat_string alternating caps, reversed")
    void testAlphabetsOnly() {
        // input: [a, y, b] → uppercased: [A, Y, B] → reversed [B, Y, A] → "ByA"
        BfhlRequest req = new BfhlRequest(Arrays.asList("a", "y", "b"));
        BfhlResponse res = service.processData(req);

        assertTrue(res.isSuccess());
        assertEquals(List.of("A", "Y", "B"), res.getAlphabets());
        assertTrue(res.getOddNumbers().isEmpty());
        assertTrue(res.getEvenNumbers().isEmpty());
        assertTrue(res.getSpecialCharacters().isEmpty());
        assertEquals("0", res.getSum());
        assertEquals("ByA", res.getConcatString());
    }

    // ── Personal details ──────────────────────────────────────────────────────
    @Test
    @DisplayName("Personal details are correct in every response")
    void testPersonalDetails() {
        BfhlRequest req = new BfhlRequest(Collections.emptyList());
        BfhlResponse res = service.processData(req);

        assertEquals("atharvya_sharma_01062006", res.getUserId());
        assertEquals("atharvyasharma230072@acropolis.in", res.getEmail());
        assertEquals("0827AL231037", res.getRollNumber());
    }
}
