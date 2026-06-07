[![REST Assured API Tests](https://github.com/shilpa-soni-qa/rest-assured-portfolio/actions/workflows/main.yml/badge.svg)](https://github.com/shilpa-soni-qa/rest-assured-portfolio/actions/workflows/main.yml)

# REST Assured Portfolio — Shilpa Soni

REST API Testing using REST Assured (Java) + TestNG

## 🛠️ Tools Used
- REST Assured 5.3.0
- TestNG 7.8.0
- Java
- Maven
- IntelliJ IDEA

## 📁 Project Structure
- `BaseTest.java` — Common setup, Request and Response specifications
- `UserApiTest.java` — 9 tests for Users API
- `PostApiTest.java` — 5 tests for Posts API
- `testng.xml` — Test suite configuration

## ✅ Test Coverage

### UserApiTest
| Test | Method | Status |
|------|--------|--------|
| testGetAllUsers | GET | ✅ |
| testGetSingleUser | GET | ✅ |
| testCreateUser | POST | ✅ |
| testUpdateUser | PUT | ✅ |
| testDeleteUser | DELETE | ✅ |
| testGetInvalidUser | GET 404 | ✅ |
| testExtractUserDetails | GET | ✅ |
| testHamcrestMatchers | GET | ✅ |
| testWithAuthentication | GET | ✅ |

### PostApiTest
| Test | Method | Status |
|------|--------|--------|
| testGetAllPosts | GET | ✅ |
| testGetSinglePost | GET | ✅ |
| testCreatePost | POST | ✅ |
| testUpdatePost | PUT | ✅ |
| testDeletePost | DELETE | ✅ |

## 🎯 Key Features
- Base class for reusable setup
- Request Specification — reusable headers
- Response Specification — reusable assertions
- JSON Path extraction
- Hamcrest matchers — advanced assertions
- Bearer token authentication
- TestNG XML suite runner

## 📊 Test Results
- Total Tests: 14
- Passing: 14
- Failing: 0

## 🔗 How to Run
1. Clone the repository
2. Open in IntelliJ IDEA
3. Run `testng.xml`
4. All 14 tests should pass ✅

## 👩‍💻 Author
**Shilpa Soni** — QA Automation Engineer
GitHub: https://github.com/shilpa-soni-qa
LinkedIn: https://www.linkedin.com/in/shilpasoni94
