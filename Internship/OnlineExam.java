import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Quiz {
    private static final int QUESTION_COUNT = 5;
    private static final int TIME_LIMIT_SECONDS = 60;

    private static final String[] QUESTIONS = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the largest mammal in the world?",
            "What is the currency of Japan?"
    };

    private static final String[] OPTIONS = {
            "A. Paris   B. London   C. Berlin",
            "A. Venus   B. Mars     C. Jupiter",
            "A. Shakespeare  B. Dickens   C. Hemingway",
            "A. Elephant    B. Blue Whale    C. Giraffe",
            "A. Yen    B. Won    C. Ringgit"
    };

    private static final char[] ANSWERS = { 'A', 'B', 'A', 'B', 'A' };

    public void startQuiz(User user) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("Welcome, " + user.getUsername() + "! Let's start the quiz.");

        for (int i = 0; i < QUESTION_COUNT; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + QUESTIONS[i]);
            System.out.println(OPTIONS[i]);

            char userAnswer = ' ';
            while (userAnswer != 'A' && userAnswer != 'B' && userAnswer != 'C') {
                System.out.print("Your answer (A/B/C): ");
                userAnswer = scanner.next().toUpperCase().charAt(0);
            }

            if (userAnswer == ANSWERS[i]) {
                System.out.println("Correct! You earned a point.");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + ANSWERS[i] + ".");
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTimeSeconds = (endTime - startTime) / 1000;

        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + " out of " + QUESTION_COUNT);
        System.out.println("Time taken: " + elapsedTimeSeconds + " seconds");

        if (elapsedTimeSeconds < TIME_LIMIT_SECONDS) {
            System.out.println("Quiz submitted within time limit.");
        } else {
            System.out.println("Quiz time exceeded. Auto-submitted.");
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User registration (for simplicity, username and password are hardcoded)
        User user = new User("john_doe", "password123");

        // Login
        System.out.print("Enter your username: ");
        String enteredUsername = scanner.next();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.next();

        if (enteredUsername.equals(user.getUsername()) && enteredPassword.equals(user.getPassword())) {
            Quiz quiz = new Quiz();

            // Profile update (not implemented in this example)
            System.out.println("Profile updated successfully.");

            // Start the quiz
            quiz.startQuiz(user);

            // Logout
            System.out.println("Logout successful.");
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }
}
