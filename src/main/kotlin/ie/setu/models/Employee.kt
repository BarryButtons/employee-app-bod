package ie.setu.models

import kotlin.math.round

class Employee (var firstName: String,
                var surname: String,
                var gender:Char,
                var employeeID: Int,
                var grossSalary: Double,
                var payePercentage: Double,
                var prsiPercentage: Double,
                var annualBonus: Double,
                var cycleToWorkMonthlyDeduction: Double){
    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. ${firstName} ${surname}"
        'f', 'F' -> "Ms. ${firstName} ${surname}"
        else -> "${firstName} ${surname}"
    }



    fun roundTwoDecimals(number: Double) = round(number * 100) / 100


    fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun getTotalBonus() = roundTwoDecimals(grossSalary * (annualBonus / 100))
    fun getMonthlyBonus() = roundTwoDecimals(getTotalBonus()/12)
    fun getMonthlyPrsi() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
    fun getMonthlyPaye() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
    fun getGrossPay() = roundTwoDecimals(getMonthlySalary() + getMonthlyBonus())
    fun getTotalDeductions() = roundTwoDecimals((getMonthlyPrsi() + getMonthlyPaye() + cycleToWorkMonthlyDeduction))
    fun getNetPay() = roundTwoDecimals(roundTwoDecimals(getGrossPay() - getTotalDeductions()))
    fun getPayslip() = """

   
 Monthly Payslip
----------------------------------------------------                                                   
 Employee Name:  ${getFullName()}     Gender:${gender.uppercase()}                 
 Employee ID: ${employeeID}     
----------------------------------------------------  
  PAYMENT DETAILS
 Salary: ${getMonthlySalary()} 
 Bonus:  ${getMonthlyBonus()}
 
 Gross: ${getGrossPay()}
----------------------------------------------------    
  DEDUCTION DETAILS
 PAYE: ${getMonthlyPaye()} 
 PRSI: ${getMonthlyPrsi()} 
 Cycle To Work:  ${cycleToWorkMonthlyDeduction}
  
 Total Deductions: ${getTotalDeductions()}
----------------------------------------------------   
 Gross:  ${getGrossPay()}  
 - Total Deductions: ${getTotalDeductions()} 
----------------------------------------------------   
 NET PAY:${getNetPay()} 
   
"""

    override fun toString(): String {
        return "Employee(firstName='$firstName', surname='$surname', gender=$gender, employeeID=$employeeID, grossSalary=$grossSalary, payePercentage=$payePercentage, prsiPercentage=$prsiPercentage, annualBonus=$annualBonus, cycleToWorkMonthlyDeduction=$cycleToWorkMonthlyDeduction)"
    }
}

