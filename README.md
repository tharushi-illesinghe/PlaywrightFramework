# Playwright Java QA Framework

## Overview
This repository contains a **Java-based automation framework** using **Playwright** and **TestNG** for end-to-end web application testing. It is built with the **Page Object Model (POM)** design pattern and supports **cross-browser testing** (Chrome, Edge).

The framework includes **ExtentReports** integration for detailed HTML reporting with **screenshots on failure**. Sample tests verify product pages, similar items, and UI functionality on an e-commerce platform (eBay).

---

## Features
- Playwright + Java + TestNG for robust automation.
- Cross-browser support: Chrome, Edge.
- Page Object Model (POM) for maintainable and reusable code.
- ExtentReports integration with screenshots on test failure.
- Configurable via TestNG XML for different browsers.
- Utility classes for browser setup, reporting, and common actions.

---

## Tech Stack
- **Language:** Java 17
- **Test Framework:** TestNG
- **Automation Library:** Microsoft Playwright
- **Reporting:** ExtentReports
- **Build Tool:** Maven

---

## Project Structure
PlaywrightJavaQAFramework/
├── src/main/java
│ └── base/ # Base classes for setup and teardown
├── src/test/java
│ ├── pages/ # Page Object Model classes
│ ├── tests/ # Test classes
│ └── utils/ # Utilities (PlaywrightFactory, ReportManager)
├── pom.xml # Maven dependencies
└── testng.xml # TestNG suite for running tests


---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.x
- Microsoft Playwright
- IntelliJ IDEA or similar IDE

### Installation
Clone the repository:
```bash 
git clone https://github.com/tharushi-illesinghe/PlaywrightFramework.git
```
Install dependencies:
```bash 
mvn clean install
```


### Running Tests
Run via TestNG XML (Cross-browser)

Go to testng.xml file and execute for Cross-browser testing


### Test Reports
Reports are generated at:

- reports/ExtentReport.html


Reports include:
- Test steps and logs
- Pass/Fail/Skip status
- Screenshots on failure
- Browser used
