def clientName = client.name == null ? "default" : client.name // Java ternary using Groovy properties
def clientName2 = client.name ?: "default"