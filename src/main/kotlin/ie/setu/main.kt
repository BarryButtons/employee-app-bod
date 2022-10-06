package ie.setu

import kotlin.math.round

var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 8.2, 54.33)

fun main(args: Array<String>){

    var input : Int

    fun add(){
        print("Enter first name: ")
        val firstName = readLine().toString()
        print("Enter surname: ")
        val surname = readLine().toString()
        print("Enter gender (m/f): ")
        val gender = readLine()!!.toCharArray()[0]
        print("Enter employee ID: ")
        val employeeID = readLine()!!.toInt()
        print("Enter gross salary: ")
        val grossSalary = readLine()!!.toDouble()
        print("Enter PAYE %: ")
        val payePercentage = readLine()!!.toDouble()
        print("Enter PRSI %: ")
        val prsiPercentage = readLine()!!.toDouble()
        print("Enter Annual Bonus: ")
        val annualBonus= readLine()!!.toDouble()
        print("Enter Cycle to Work Deduction: ")
        val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

        employee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
    }

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPrsi()}")
            3 ->println("Monthly PAYE: ${getMonthlyPaye()}")
            4 -> println("Monthly Gross Pay: ${getGrossPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetPay()}")
            7 -> println("Monthly Bonus:${getTotalBonus()}")
            8 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Monthly Bonus
           8. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
}

fun getFullName() = when (employee.gender) {
    'm', 'M' -> "Mr. ${employee.firstName} ${employee.surname}"
    'f', 'F' -> "Ms. ${employee.firstName} ${employee.surname}"
    else -> "${employee.firstName} ${employee.surname}"
}

fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
fun getTotalBonus() = roundTwoDecimals(employee.grossSalary * (employee.annualBonus / 100))
fun getMonthlyBonus() = roundTwoDecimals(getTotalBonus()/12)
fun getMonthlyPrsi() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
fun getMonthlyPaye() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
fun getGrossPay() = roundTwoDecimals(getMonthlySalary() + getMonthlyBonus())
fun getTotalDeductions() = roundTwoDecimals((getMonthlyPrsi() + getMonthlyPaye() + employee.cycleToWorkMonthlyDeduction))
fun getNetPay() = roundTwoDecimals(roundTwoDecimals(getGrossPay() - getTotalDeductions()))
fun getPayslip() = """

   
 Monthly Payslip
----------------------------------------------------                                                   
 Employee Name:  ${getFullName()}     Gender:${employee.gender.uppercase()}                 
 Employee ID: ${employee.employeeID}     
----------------------------------------------------  
  PAYMENT DETAILS
 Salary: ${getMonthlySalary()} 
 Bonus:  ${getMonthlyBonus()}
----------------------------------------------------    
  DEDUCTION DETAILS
 PAYE: ${getMonthlyPaye()} 
 PRSI: ${getMonthlyPrsi()} 
 Cycle To Work:  ${employee.cycleToWorkMonthlyDeduction} 
----------------------------------------------------   
 Gross:  ${getGrossPay()}  
 Total Deductions: ${getTotalDeductions()} 
----------------------------------------------------   
 NET PAY:${getNetPay()} 
   
"""