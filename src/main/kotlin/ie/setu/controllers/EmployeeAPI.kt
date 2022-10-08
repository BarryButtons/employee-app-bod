package ie.setu.controllers

import ie.setu.models.Employee

var lastId= 1001
//var paye = 38.2

internal fun getId(): Int {
    return lastId++
}

/*internal fun getpaye(): Double {
    return paye
}*/

class EmployeeAPI{

    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee>{
        return employees
    }

    fun findOne(id:Int): Employee? {
        return employees.find {p -> p.employeeID == id}
    }

    fun create(employee: Employee){
        employee.employeeID = getId()
        //employee.payePercentage= getpaye()
        employees.add(employee)
    }
}