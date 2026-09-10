## Senior Backend - Challenge (Any Programming language) (60 mins)

## ⛔ Thing to keep in mind

- **Chatgpt (or similar tools) and google use is not allowed.**
- You are required to keep your camera on and share your entire screen  throughout the challenge
- Once you are done, push your project on a github link and make it private and share it with us

## **🧠 What's the Mission?**

**Problem Statement : Develop a URL Shortening Service API**

### ⌛ Max time: 1 Hour

### 📜 Asks

Your task is to design and implement a simplified API for a URL shortening service. Users can submit long URLs, and the service will generate a shorter, unique alias for each URL.

The API should provide the following functionality:

1. **Shorten URL**: Users can submit a long URL, and the service will generate a unique short alias for it.
2. **Redirect**: Users can use the short alias to redirect to the original long URL.
3. **Stats**: Users can view statistics for a short alias, including the number of times it has been accessed and the creation date. **This is optional and should be done at the end only if time permits.**

Additionally, the following requirements should be implemented:

1. **Request Logging Middleware**: Implement middleware to log the API request method, URL, and timestamp for every request.
2. **Rate Limiting**: Include rate limiting to prevent brute force attacks. Set a maximum limit of requests per minute to mitigate potential abuse of the API.

### 🚨 Constraints:

- Use any programming language or framework of your choice.
- Ensure error handling for cases such as invalid URLs, non-existent aliases, rate limiting violations, etc.
- Design the API endpoints following RESTful principles.
- Make sure the API responses are in JSON format.

### 🎯 Evaluation Criteria:

1. **Functionality**: Does the API meet the specified requirements for a URL shortening service? Are the middleware and rate limiting functionalities implemented correctly?
2. **Design**: Is the API well-designed and follows RESTful principles? Is the request logging middleware implemented appropriately?
3. **Error Handling**: Are potential error cases handled gracefully with appropriate error messages?
4. **Code Quality**: Is the code clean, readable, and well-organized?

### 📒 Note to the Candidate:

Focus on implementing the core functionality of the URL shortening service along with the additional requirements within the given time frame. The middleware for request logging and rate limiting should be implemented to enhance the security and monitoring capabilities of the API.

1. ***Shorten URL*** : Users can submit a long URL, and the service will generate a unique short alias for it.
2. ***Redirect* :** Users can use the short alias to redirect to the original long URL.
3. ***Stats* :** Users can view statistics for a short alias, including the number of times it has been accessed and the creation date.

**(*Code needs to be pushed on github within the allotted time)***
