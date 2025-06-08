# RestAssured_Practise

RestAssured Practice Project Setup
Prerequisites

Java 8 or higher
Maven
Postman API Key

Setup Instructions

1. Clone the Repository
bashgit clone <your-repo-url>
cd RestAssured_Practice

2. Configure API Key
create config.properties under src/test/resources and replace YOUR_API_KEY_HERE with your actual Postman API key:
propertiesapi.key=YOUR_ACTUAL_API_KEY_HERE


3. Run Tests
mvn test

Important Notes

Never commit the config.properties file to version control
The config.properties file is already added to .gitignore
Always use the template file when setting up on a new machine
Keep your API keys secure and never share them publicly
