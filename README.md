Smart Home devices by sector ID
===============================
Author: Andres Solorzano.    
Level: Basic.    
Technologies: Java, Apache Maven, DynamoDB, Lambda Functions, JSON API, and Log4J.    
Summary: Lambda function that return electronic devices in a Smart City platform.    
Target Product: Lambda Function with Java 8.
 

What is it?
-----------

Lambda function that return electronic devices in a Smart City platform.



## Requirements

* AWS CLI already configured with at least PowerUser permission
* [Java SE Development Kit 11 installed]
* [Maven](https://maven.apache.org/install.html)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)



## Setup process

### Installing dependencies

We use `maven` to install our dependencies and package our application into a JAR file:

```bash
mvn clean package
```


## Packaging and deployment

Firstly, we need a `S3 bucket` where we can upload our Lambda functions packaged as ZIP before we
deploy anything - If you don't have a S3 bucket to store code artifacts then this is a good time to
create one...

Next, run the following command to package our Lambda function to S3:

```bash
sam package --template-file template.yaml --output-template-file packaged.yaml --s3-bucket <BUCKET_NAME>
```

Next, the following command will create a CloudFormation Stack and deploy your SAM resources.

```bash
sam deploy --template-file packaged.yaml --stack-name devices-sector-function-stack --capabilities CAPABILITY_IAM
```

> **See [Serverless Application Model (SAM) HOWTO Guide](https://github.com/awslabs/serverless-application-model/blob/master/HOWTO.md) for more details in how to get started.**


After deployment is complete you can run the following command to retrieve the API Gateway EndPoint URL:

```bash
aws cloudformation describe-stacks --stack-name sam-devices-function --query 'Stacks[].Outputs'
```