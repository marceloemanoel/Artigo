//-Operador ternário de Java utilizando Groovy Properties+
def clientName = client.name == null ? "default" : client.name 

//-Versão do código acima, utilizando o operador elvis+
def clientName2 = client.name ?: "default"