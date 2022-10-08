package ie.setu

import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging

var employees = EmployeeAPI()

val logger = KotlinLogging.logger {}
fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
        start()
    }

fun menu() : Int {
    print(
        """
    |Employee Menu
    |   1.Add Employee
    |   2.List All Employee
    |   3.Search Employee
    |   4.Print Payslip for Employee
    |   0.Exit
    |   
    |Enter Option:
    """.trimMargin()
    )
    return readLine()!!.toInt()
}


fun start(){
    var input:Int

    do{
        input=menu()
        when (input){
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()

            0 -> println("Exiting App")
            else-> println("Invalid Option")
        }
        println()
    }while (input != 0)
}

    fun list(){
        employees.findAll()
            .forEach{ println(it) }
    }


fun search() {
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()

    print("Enter surname: ")
    val surname = readLine().toString()

    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]

    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()

    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()

    print("Enter Annual Bonus %: ")
    val annualBonus= readLine()!!.toDouble()

    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employees.create(Employee(firstName, surname, gender, 0, grossSalary, 38.2, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}

