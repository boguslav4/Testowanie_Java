Scenario: Looking for link
Given you are on the homepage
When you are looking for link
Then link Notebooki is found

Scenario: Image loading test
Given you are on the homepage again
When you are waiting for image to load
Then image will be loaded

Scenario: Selector work as planned
Given you are on the homepage last
When you click on checkbox
Then you are on next page

Scenario: Font-size is proper
Given you are on the homepage last time
When you take css value
Then you check if proper