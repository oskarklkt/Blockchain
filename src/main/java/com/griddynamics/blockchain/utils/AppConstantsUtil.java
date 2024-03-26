package com.griddynamics.blockchain.utils;

import com.griddynamics.blockchain.constants.AppConstants;
import com.griddynamics.blockchain.constants.OutputMessages;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class AppConstantsUtil {
  public static void setRequiredTrailingZeros() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(OutputMessages.GET_REQUIRED_TRAILING_ZEROS);
    AppConstants.REQUIRED_TRAILING_ZEROS = scanner.nextInt();
    log.info(System.lineSeparator());
  }
}
