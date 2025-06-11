# ModularPersonalFinanceManager

A **modular, extensible, and maintainable Java-based personal finance manager**. This project is build with Gradle and structured to showcase a clean, layered architecture.

---

## 💡 Project Goals
- Practice modular design and clean architecture
- Build a scalable and testable finance management application
- Professional development workflow

---

## 🧱 Tech Stack
- **Java 17**
- **Gradle** (Kotlin DSL)
- **JUnit 5** for testing
- **Gson** for JSON serialization
- **Modular project structure** (domain, service and UI layers)

---

## 📦 Current Modules
- `entry`: domain model for financial entries (amount, description, date, type)
- `entryType`: defines types like `INCOME`, `EXPENSE`

---

## 🔧 How to Build
```bash
./gradlew clean build
