# 🧪 API Test Automation Framework

This is my personal API Testing Framework, designed to sharpen my API testing skills.    
---

## Requisites

- ☕ Java 21+
- 🔧 Gradle 8+

---

## Services under test

- Spotify
- GoRest
- WireMock (locally)
---



## 🚀 How to Run

1. **Clone the repo**:
   ```bash
   git clone https://github.com/edwinchp/api-testing-framework.git
   cd /api-testing-framework
   ```

2. **Define environment variables** (Replace .env with your information)
   ```bash
   cp .env_example .env
   ```

3. **Set environment variables** (set only for dev and testing environments)
   ```bash
   export $(grep -v '^#' .env | xargs)
   ```
   
4. **Run Tests**
   ```bash
   gradle clean smokeTest
   ```

5. **Open reports**
   ```
   open build/cucumber-reports.html       # macOS
   xdg-open build/cucumber-reports.html   # Linux
   start build/cucumber-reports.html      # Windows
   ```
