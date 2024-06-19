# chat-socketio

Sample project to show a multiagent chat using socket io backend.

## Steps
- Clone https://github.com/socketio/chat-example.git repository.
- `npm i && npm start`

## Problems and approaches
- Socket is not connecting right now. I have tried to use socket.io library and bare metal socket raising connection rejected exceptions.
- Messages are not parsed correctly since the socket is not up and running.
- Ordered messages are not guaranteed. We should enforce that from the data layer.

## Future dev work
- [ ] Debug socket connection to run without errors.
- [ ] Add Unit Tests to main classes in the project.
- [ ] Handle errors with Result class or Arrow Monads.
- [ ] Split package layers into different modules.
- [ ] Introduce Use Cases to connect with data layer.
- [ ] Split into horizontal modules by feature when the app gets more features.
- [ ] Include static analysis code tools to ensure the quality in the repo.
- [ ] Migrate MVVM to MVI.

## Upcoming features (6 months)
- [ ] Include local storage to save messages when there is no internet connection.
- [ ] Include capability to have media over the messages.
- [ ] Create user profile for messaging.
- [ ] Handle online/offline status.

## Strategy notes
### Testing
- The testing strategy for the repo should follow the typical testing pyramid. 
- As we could take 80% of code coverage, we should enforce the quality of the tests.
- Critical classes, as repositories or view models, must have the main flows covered by tests.
- All unit tests must have its dependencies with a double, can be mock or fake depending on the needs.
- Integration tests should cover main flows of the app, mocking final data sources as could be the api or the socket in this case.
- For the UI layer, we should start with Screenshot testing as it covers the major needs for this layer.

## CI/CD and Branching strategy
- We are going to use Github as remote repository.
- We need to follow GitFlow principles with the basic branches: 
  - Main: Storing released versions.
  - Develop: Working branch.
  - Release: Each version branched of develop and merged to main and back to develop when released.
  - Feature/Fix: Aux branches to code and merged back to develop when ready.
- We need to include a basic pipeline using Bitrise with Build, Test and Deploy steps.
- As the app grows, we need to include features flags to control the stability and availability of the features in production.
- 