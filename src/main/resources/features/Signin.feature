Feature: Amazon User SignIn
  As a Registered User on Amazon website
  I want to be able to Signin using valid details
  So that I can see my Account

  Background: Pre-requisite
    Given User navigates to Amazon
    Then User should be on Amazon page

  @signUp
  Scenario: Signing in as a valid User
    Given User Signin using "piuser@mailinator.com" and Password "abcd1234"
    Then User Should Signin Successful

  @signIn
  Scenario Outline: Invalid User Signin Validation Messages
    Given User Signin using "<Email>" and Password "<Password>"
    Then Notification Message should be <Message>

    Examples: User Details
      | Email            | Password | Message                                            |
      | test@test.com    |          | "Enter your password"                              |
      |                  | abcd1234 | "Enter your e-mail address or mobile phone number" |



