package ru.example;

import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author ogbozoyan
 * @since 11.02.2024
 */
public class CronValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print("Enter cron expression (or type 'exit' to quit): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                CronExpression cronExpression = CronExpression.parse(input);
                System.out.println("Cron Expression: " + input);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime nextExecutionTime = cronExpression.next(now);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                System.out.println("Next few instances:");
                for (int i = 0; i < 5; i++) {
                    System.out.println(formatter.format(nextExecutionTime));
                    nextExecutionTime = cronExpression.next(nextExecutionTime);
                }
            } catch (Exception e) {
                System.out.println("Invalid cron expression");
            }
        } while (true);
    }
}
