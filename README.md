# Description

    This program takes an expression to be evaluated and 6 input arrays. Returns sum of evaluated expression 
    based on grouping.

    void evalExpr(string expr, double [] arr1, double [] arr2, double [] arr3, double [] arr4, string [] region, string [] country)

    - expr string is of the form : "a1*a2 + a3/a4" (any combination of variables a1, a2, a3, a4. Operators are +, -, *, /)
    - Arrays arr1, ..., arr4 each contain doubles
    - Array region contains names of continents
    - Array country contains names of countries
    - All arrays have same number of elements (10 million or more)

    expr needs to be applied to elements of arrays (arr1, arr2, arr3, arr4). Then result needs 
    to be grouped (summed up) based on region and country.

    Example: If the expression is "a1 + a2 + a3 + a4" and the 6 arrays are:

    arr1           arr2            arr3            arr4         region        country

    1.0            2.0              3.0             4.0          asia            india
    5.0            6.0              7.0             8.0          europe       france
    9.0           10.0            11.0            12.0         asia           india
    13.0         14.0             15.0           16.0        europe       germany
    17.0          18.0            19.0           20.0        europe       france

    Output of the program would be

    asia       india        52.0
    europe  france      100.0
    europe  germany   58.0


# Taking Input

The input file contains the input lines like this

    1.0 2.0 3.0 4.0 asia india
    5.0 6.0 7.0 8.0 europe france
    9.0 10.0 11.0 12.0 asia india
    13.0 14.0 15.0 16.0 europe germany
    17.0 18.0 19.0 20.0 europe france

There are different files depending on the number of lines of input in each file like 

    Inputs.txt contains 5 lines
    Inputs_1_million.txt contains 1 million lines
    Inputs_5_million.txt contains 5 million lines
    Inputs_10_million.txt contains 10 million lines
    
The input files are stored at "/Evaluate Expression/Inputs/" folder, the files are in zip format(I have stored them in zip format to compress the files because otherwise the file sizes were like 30MB, 150MB, 300MB but github has an file upload limit of 100MB) which are containing the input lines, you have to unzip or extract them before using them. I have extracted one file i.e., Inputs_1_million.txt(size 30MB) for you.

There are 2 ways to specify the expression and path of the input file in this program 
1. by providing the expression and path in CLI

       java App "a1+a2+a3+a4" "C:\Users\veer5\IdeaProjects\Evaluate Expression\Inputs\Inputs_1_million.txt"
   
3. default(by not providing any input in CLI)

       java App
   it will take the default value of the expression i.e. a1+a2+a3+a4 and the path i.e. C:\Users\veer5\IdeaProjects\Evaluate Expression\Inputs\Inputs_1_million.txt
   

# Output for 1 million input size

![Multi 1 million](https://user-images.githubusercontent.com/42229671/148639995-6b9642d9-d23b-468d-9622-3cf40ca88dfb.PNG)

-----------------------------------------------------------------------------------------------------------------------------------------------

# Output for 5 million input size
      
![Multi 5 million](https://user-images.githubusercontent.com/42229671/148640029-a19a4ccd-7a37-40c5-b72a-e9708fa080e0.PNG)

------------------------------------------------------------------------------------------------------------------------------------------------

# Output for 10 million input size

![Multi 10 million](https://user-images.githubusercontent.com/42229671/148640031-7dc4eb19-eb71-4cb7-84be-065fbcd68572.PNG)

