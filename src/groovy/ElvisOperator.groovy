// Java ternary using Groovy properties
def clientName = client.name == null ? "default" : client.name 

//Elvis operator version
def clientName2 = client.name ?: "default"