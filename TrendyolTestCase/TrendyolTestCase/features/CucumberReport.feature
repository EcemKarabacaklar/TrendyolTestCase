Feature: Cucumber Report

#GET
  Scenario: First Get Book Request
    Given Get Book Request
    When Is Repository Empty
    Then "Yes" Repository Is Empty

  Scenario: Get Book Request By Id
    Given Book True
    When Get Book Request By Id
    Then Book found

  Scenario: Get Book Request By Id
    Given Book False
    When Get Book Request By Id
    Then Book not found

#POST
  Scenario: Title And Author are required fields
    Given Title Is Not Null AND Author Is Null
    When Insert Book Request
    Then Field "author" is required

  Scenario: Title And Author are required fields
    Given Title Is Null AND Author Is Not Null
    When Insert Book Request
    Then Field "title" is required

  Scenario: Title And Author are required fields
    Given Title Is Not Empty AND Author Is Empty
    When Insert Book Request
    Then Field "author" cannot be empty

  Scenario: Title And Author are required fields
    Given Title Is Empty AND Author Is Not Empty
    When Insert Book Request
    Then Field "title" cannot be empty

  Scenario: Title And Author are required fields
    Given Title Is Not Empty AND Author Is Not Empty
    When Insert Book Request
    Then Success

  Scenario: Create New Book and Verify
    Given Valid Book Model
    When Insert Book Request AND Get Book Request By Id
    Then IsSame



