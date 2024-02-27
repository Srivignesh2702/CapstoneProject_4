Feature: Verify Page Elements

  Scenario: Verify title and elements on the homepage
    Given I am on the home page
    Then I should see the title as "The Internet"
    When I click on "A/B Testing" link
    Then I should see text as "A/B Test Variation 1"
    When I navigate back to the homepage
    And I click on "Dropdown" dropdown link
    And I select "Option 1" from dropdown
    Then I should see "Option 1" selected
    When I navigate back to the homepage
    And I click on "Frames" link
    Then I should see "Nested Frames" link
    And I should see "iFrame" link in the page
