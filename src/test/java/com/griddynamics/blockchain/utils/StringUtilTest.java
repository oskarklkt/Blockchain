package com.griddynamics.blockchain.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {
  @Test
  void applySha256WithKnownString() {
    // given
    String expectedHash = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824";
    String actualHash = StringUtil.applySha256("hello");
    // then
    assertEquals(expectedHash, actualHash);
  }

  @Test
  void applySha256WithEmptyString() {
    // given
    String expectedHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
    String actualHash = StringUtil.applySha256("");
    // then
    assertEquals(expectedHash, actualHash);
  }

  @Test
  void applySha256IsConsistent() {
    // given
    String input = "consistency check";
    String hash1 = StringUtil.applySha256(input);
    String hash2 = StringUtil.applySha256(input);
    // then
    assertEquals(hash1, hash2);
  }
}
