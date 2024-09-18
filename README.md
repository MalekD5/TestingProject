# Task Management

Task Management (TM) is a Command line interface
application used to manage tasks. This system includes task
categorization and prioritization.

Made By:
- Rashad Ibrahim Rashad Altal (2136236)
- Malek Mohammad Shawahneh (2138257)


## Setup instructions

This project must be imported as a gradle project.
assuming you are using Eclipse:

first you will need Install eclipse gradle (buildship) tooling

1. File -> import ![img](/imgs/step1.png)
2. Choose gradle project ![img2](/imgs/import_wizard.png)
3. Specify your root project ![img3](/imgs/import_gradle_project.png)
4. Press finish ![img4](/imgs/import_options_gradle.png)

you can run tests through Eclipse or from gradle CLI
`./gradlew test` or in windows `./gradlew.exe test`

# Design Choices

## 1. Command Pattern Implementation:

**Files Involved:** `Main.java`, `CommandManager.java`, `CommandDispatcher.java`, `ICommand.java`, and command implementations (e.g., `LoginCommand.java`).

**Explanation:**
- The command pattern is used to encapsulate requests as objects, allowing the application to parameterize, queue, and execute commands.
- The `Main.java` class reads user input from the command line, parses it into commands and arguments, and delegates execution to the `CommandManager`.
- `CommandManager` retrieves the appropriate `ICommand` implementation based on the user input.
- Each command (e.g., `LoginCommand`, `ExitCommand`) implements the `ICommand` interface, which enforces a standard method for executing commands. This approach promotes scalability and maintainability, as new commands can be added easily without modifying the core logic.

## 2. Application Lifecycle Management:

**Files Involved:** `ApplicationManager.java`

**Explanation:**
- The `ApplicationManager` class appears to be responsible for managing the state of the application, including user sessions and overall application lifecycle.
- This class likely handles the "logged in" state, user management, and possibly interactions between different components of the application. By centralizing these responsibilities, the application can maintain a clear separation of concerns.

## 3. Task Management:

**Files Involved:** `TaskManager.java`, `Task.java`

**Explanation:**
- The `TaskManager` class manages a list of tasks, providing methods to add, retrieve, delete, and filter tasks. The design supports searching for tasks by category, priority, or name, as well as marking tasks as completed.
- This design encapsulates task-related functionality within a dedicated class, adhering to the single responsibility principle. It allows the application to handle task-related operations in a centralized and consistent manner.

## 4. Authentication Management:

**Files Involved:** `AuthService.java`, `User.java`

**Explanation:**
- The `AuthService` class likely manages user authentication, including login and registration processes. It interacts with user data (`User` class) and possibly a database layer.
- The authentication logic is separated from other business logic, promoting modularity and reusability. By keeping authentication isolated, changes to the authentication process won't affect other parts of the application.

## 5. Custom Exception Handling:

**Files Involved:** Custom exceptions like `DuplicateUsernameException`, `InvalidPasswordException`, etc.

**Explanation:**
- Custom exceptions are used to handle specific error conditions in a meaningful way. For example, `DuplicateUsernameException` might be thrown during user registration if the username is already taken.
- Using custom exceptions improves code readability and error handling by providing clear, domain-specific error messages.

## 6. User Input and Interaction:

**Files Involved:** `Main.java`

**Explanation:**
- The `Main.java` file handles user interaction via the command line interface (CLI). It continuously reads user input, processes it, and dispatches commands accordingly.
- The design prioritizes user experience by providing immediate feedback for invalid commands and maintaining a loop for continuous interaction until an exit command is issued.

## 7. Separation of Concerns:

**Files Involved:** Across all packages and classes.

**Explanation:**
- The project is organized into different packages (`auth`, `command`, `tasks`, `exceptions`, etc.), each handling a specific aspect of the application. This separation of concerns ensures that each component of the application is responsible for a distinct part of the overall functionality.
- This design choice makes the codebase easier to navigate, maintain, and extend, as changes in one part of the application are less likely to impact other areas.
