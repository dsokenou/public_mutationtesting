# Mutation Testing Playground

The project contains examples for mutation testing. Tested application is an uncomplete stock backend implementation. The example demonstrates the difference between (good) code coverage and mutation coverage.

## How to run mutation tests

To only run mutation tests, use:
`.\mvnw pitest:mutationCoverage`

If you don't run tests or install before running mutation tests, use:
`.\mvnw test-compile pitest:mutationCoverage`
