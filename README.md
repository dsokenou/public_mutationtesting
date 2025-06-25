# Mutation Testing Playground

The project contains examples for mutation testing. 
Tested application is an incomplete stock backend implementation. 
The example demonstrates the difference between (good) code coverage and mutation coverage.

Follow `TODO`s in code to find the places that should be improved for better mutation coverage.

## How to run mutation tests

To only run mutation tests, use:
`.\mvnw pitest:mutationCoverage`

If you don't run tests or install before running mutation tests, use:
`.\mvnw test-compile pitest:mutationCoverage`
