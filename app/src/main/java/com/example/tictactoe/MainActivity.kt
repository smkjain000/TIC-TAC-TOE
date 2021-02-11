package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var PLAYER=true
    var TURN_COUNT=0
    var boardstatus=Array(3){IntArray(3)}


    lateinit var board: Array<Array<Button>> //declare board
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var TextView=findViewById<TextView>(R.id.playerturn)

        board= arrayOf(
                
                arrayOf(findViewById(R.id.button),findViewById(R.id.button2),findViewById(R.id.button3)),
                arrayOf(findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6)),
                arrayOf(findViewById(R.id.button7),findViewById(R.id.button8),findViewById(R.id.button9)),
        )

        for(i:Array<Button> in board){
            for(button:Button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardstatus()

        var reset=findViewById<Button>(R.id.reset)
        reset.setOnClickListener{

            PLAYER=true
            TURN_COUNT=0
            initializeBoardstatus()
            updatedisplay("Player X Turn")

        }
    }


    private fun initializeBoardstatus() {
        for(i in 0..2){
            for (j in 0..2){
                boardstatus[i][j]=-1
            }
        }

        for (i:Array<Button> in board){
            for (button:Button in i){
                button.isEnabled=true
                button.text=" "
            }
        }
    }


    override fun onClick(view: View) {

        when(view.id){
            R.id.button->{
                updatevalue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updatevalue(row=0,col=1,player=PLAYER)

            }
            R.id.button3->{
                updatevalue(row=0,col=2,player=PLAYER)

            }
            R.id.button4->{
                updatevalue(row=1,col=0,player=PLAYER)

            }
            R.id.button5->{
                updatevalue(row=1,col=1,player=PLAYER)

            }
            R.id.button6->{
                updatevalue(row=1,col=2,player=PLAYER)

            }
            R.id.button7->{
                updatevalue(row=2,col=0,player=PLAYER)

            }
            R.id.button8->{
                updatevalue(row=2,col=1,player=PLAYER)

            }
            R.id.button9->{
                updatevalue(row=2,col=2,player=PLAYER)

            }
        }

        TURN_COUNT++
        PLAYER=!PLAYER
        if(PLAYER){
            updatedisplay("Player X Turn")
        }else{
            updatedisplay("Player 0 Turn")
        }
        if(TURN_COUNT==9){
            updatedisplay("Game Draw")
        }

        checkwinner()
    }

    private fun checkwinner() {
        //Horizontal Rows
        for(i in 0..2){
            if(boardstatus[i][0]==boardstatus[i][1] && (boardstatus[i][0]==boardstatus[i][2]) ){
                if(boardstatus[i][0]==1){
                    updatedisplay("Player X Wins")
                    break
                }else if(boardstatus[i][0]==0){
                    updatedisplay("Player 0 Wins")
                    break
                }
                        }
        }

        //Vertical Columns

        for(i in 0..2){
            if(boardstatus[0][i]==boardstatus[1][i] && (boardstatus[0][i]==boardstatus[2][i]) ){
                if(boardstatus[0][i]==1){
                    updatedisplay("Player X Wins")
                    break
                }else if(boardstatus[0][i]==0){
                    updatedisplay("Player 0 Wins")
                    break
                }
            }
        }

        //First Diagonal

            if(boardstatus[0][0]==boardstatus[1][1] && (boardstatus[0][0]==boardstatus[2][2]) ){
                if(boardstatus[0][0]==1){
                    updatedisplay("Player X Wins")
                }else if(boardstatus[0][0]==0){
                    updatedisplay("Player 0 Wins")
                }
            }

        //Second Diagonal

        if(boardstatus[0][2]==boardstatus[1][1] && (boardstatus[0][2]==boardstatus[2][0]) ){
            if(boardstatus[0][2]==1){
                updatedisplay("Player X Wins")
            }else if(boardstatus[0][2]==0){
                updatedisplay("Player 0 Wins")
            }
        }
    }

    private fun updatedisplay(text: String) {

        findViewById<TextView>(R.id.playerturn).text=text
        if(text.contains("Wins")){
            disablebutton()
        }

    }

    private fun disablebutton() {
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {

        val text:String=if(player) "X" else "0"
        val value:Int=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        boardstatus[row][col]=value

    }
}

