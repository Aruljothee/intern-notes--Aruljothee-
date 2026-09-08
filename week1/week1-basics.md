### What is an HTTP request?

An HTTP request is a message sent by a client, such as a browser or application, to a server to ask for something or send some data.

An HTTP request mainly contains:

Request line – tells what action we want to perform.
Headers – provide additional information about the request.
Body – contains data being sent to the server. The body is optional.

### What is an HTTP response?

An HTTP response is a message sent by the server back to the client after processing the request.

It mainly contains:

Status line – tells whether the request was successful or not.
Headers – provide additional information about the response.
Body – contains the data returned by the server. The body is optional.

### What does stateless mean in HTTP?

Stateless means that HTTP does not automatically remember previous requests.

Each request should contain the information needed by the server to process it.

### URLs: Path vs Query

A URL is the address used to locate a resource on the internet or on a server.

### What is a path?

A path identifies the resource we want to access.
Example:
/users/42 (42 can represent the ID of a particular user)
so that we can get the user whose ID is 42.

### What is a path parameter?

A path parameter is a value included in the URL path to identify a specific resource.
/users/42 (42 is the path parameter)

### What is a query parameter?

A query parameter provides additional information about how we want the server to return the data.

Query parameters come after ?
Example:
/users?role=intern (role=intern is a query parameter)
Multiple query parameters are separated by & symbol.

### Path vs Query Parameter

### Path parameter:

/users/42
it means which user?(User 42)

### Query parameter:

/users?role=intern
it means what kind of users do you want?(Intern users)
