class User {
    String firstName
    String lastName
    def printFullName = {
        println "${firstName} ${lastName}"
    }
}

// Instantiate a User using the named parameters constructor
User chris = new User(firstName:"Chris", lastName: "Judd")
User joseph = new User(firstName:"Joseph", lastName: "Nusairat")
User jim = new User(firstName:"Jim", lastName: "Shingler")

def list = [chris,joseph,jim]

println "Using collect closure:"
list.collect { it.printFullName() } //Chris Judd
                                    //Joseph Nusairat
                                    //Jim Shingler

println "\n\nUsing Spread Operator:"
list*.printFullName()   //Chris Judd
                        //Joseph Nusairat
                        //Jim Shingler
