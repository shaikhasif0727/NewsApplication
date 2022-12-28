package com.example.newsapplication

fun main() {
    val nums : IntArray = intArrayOf(3,3)
    val target = 6

    twoSum(nums = nums,target = target).forEach {
        print(it)
    }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    var result = IntArray(2)

    for (i in nums.indices)
    {
        val value = nums[i]
        for (j in i+1 until nums.size)
        {
            val value2 = nums[j]
            if(value + value2 == target)
            {
                result[0] = i
                result[1] = j
            }
        }

    }

    return result
}
