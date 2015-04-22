Scenario: Looking for link
Given you are on the homepage
When you are looking for link
Then link Notebooki is found

Scenario: Testing page look
Given you are on the notebooki
When you are making screenshot
Then screenshoot will be taken

Scenario: Image loading test
Given you are on the homepage
When you are waiting for image to load
Then image will be loaded

Scenario: Selector work as planned
Given you are on the homepage
When you click on checkbox
Then you are on next page

Scenario: Font-size is proper
Given you are on the homepage
When you take css value
Then you check if proper