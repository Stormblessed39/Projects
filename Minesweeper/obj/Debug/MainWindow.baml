   M S B A M L   `   `   `  ����F  BMinesweeper, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:MinesweeperMinesweeper  5   	       Minesweeper.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   +localclr-namespace:Minesweeper     5   	   -   5          � Title$  
MainWindow��5   	   $	��450��6   $	��800��6)   .��5	      �  � RowDefinitions 5
   
   �� 5      $	��10* �6   �� 5      $
��409* �6   .+�5   
   � -   5         �Name$ wpGameBoard��5      $
��Left=�6)   $	��305��6D   $��128,21.887,0,0q�6Q   $	��Top=�6i   $	��460��6�   $��1��6�   .+�5      �� -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -	   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -
   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5      -   5        $�� ��5      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5       -   5        $�� ��5       $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5!      -   5        $�� ��5!      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5"      -   5        $�� ��5"      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5#      -   5        $�� ��5#      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5$      -   5        $�� ��5$      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5%      -   5        $�� ��5%      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5&      -   5        $�� ��5&      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5'      -   5        $�� ��5'      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5(      -   5        $�� ��5(      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5)      -   5        $�� ��5)      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5*      -   5        $�� ��5*      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5+      -   5        $�� ��5+      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5,      -    5        $�� ��5,      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5-      -!   5        $�� ��5-      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5.      -"   5        $�� ��5.      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5/      -#   5        $�� ��5/      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 50      -$   5        $�� ��50      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 51      -%   5        $�� ��51      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 52      -&   5        $�� ��52      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 53      -'   5        $�� ��53      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 54      -(   5        $�� ��54      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 55      -)   5        $�� ��55      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 56      -*   5        $�� ��56      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 57      -+   5        $�� ��57      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 58      -,   5        $�� ��58      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 59      --   5        $�� ��59      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5:      -.   5        $�� ��5:      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5;      -/   5        $�� ��5;      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5<      -0   5        $�� ��5<      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5=      -1   5        $�� ��5=      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5>      -2   5        $�� ��5>      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5?      -3   5        $�� ��5?      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5@      -4   5        $�� ��5@      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5A      -5   5        $�� ��5A      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5B      -6   5        $�� ��5B      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5C      -7   5        $�� ��5C      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5D      -8   5        $�� ��5D      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5E      -9   5        $�� ��5E      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5F      -:   5        $�� ��5F      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5G      -;   5        $�� ��5G      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5H      -<   5        $�� ��5H      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5I      -=   5        $�� ��5I      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5J      ->   5        $�� ��5J      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5K      -?   5        $�� ��5K      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5L      -@   5        $�� ��5L      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5M      -A   5        $�� ��5M      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5N      -B   5        $�� ��5N      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5O      -C   5        $�� ��5O      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5P      -D   5        $�� ��5P      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5Q      -E   5        $�� ��5Q      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5R      -F   5        $�� ��5R      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5S      -G   5        $�� ��5S      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5T      -H   5        $�� ��5T      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5U      -I   5        $�� ��5U      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5V      -J   5        $�� ��5V      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5W      -K   5        $�� ��5W      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5X      -L   5        $�� ��5X      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5Y      -M   5        $�� ��5Y      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5Z      -N   5        $�� ��5Z      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5[      -O   5        $�� ��5[      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5\      -P   5        $�� ��5\      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   �� 5]      -Q   5        $�� ��5]      $
��Left=�6   $	��Top=�6:   ���	#FFC7F76F$��38��6i   $��46��6u   6�   �� 5^      -R   5        $�� ��5^      $
��Left=�6   $	��Top=�6:   ���	#FFAAD751$��38��6i   $��46��6u   
���   �6�   5_      �� 5`   
   -S   5        $��lblWin��5`      $��You won!��6   $
��Left=�61   $	��Top=�6L   $	��409��6d   $	��782��6q   $	��150(�6}   $��1��6�   ���	#FF23DC4D$y�Hidden=�6�   �� 5a   
   -T   5        $��Restart��5a      $
��Left=�6#   $��10,0,0,0q�6>   $��1��6P   $	��Top=�6]   $��86��6u   $��45��6�   6�   5c      5d      