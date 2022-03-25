package mx.edu.ladm_u2_p2_loteria_juanmariogonzalezborrayo

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Cartas(l:Lienzo,recurso:Int) {
    val l =l
    val imagen = BitmapFactory.decodeResource(l.resources, recurso)

    var setPinta = true
    var seArrastra=true
    var paso=0
    var cartaPaso= arrayOf(0)
    var cartaB= arrayOf(0)


     fun rand(hasta:Int):Float{
        return Random.nextInt(hasta).toFloat()
    }

    fun barajear(c:Int): Array<Int> {
        (0..c).forEach{
            var i=0
            if (paso==0){
                cartaPaso[i]=rand(52).toInt()
                cartaB[i]=cartaPaso[i]
            }
            i++
        }
        (0..c).forEach{
            var i=0
            (0..51).forEach{
                var j=0
                if (cartaB[j+1]==cartaPaso[i]){
                    cartaPaso[i]=rand(52).toInt()
                }
                j++
            }
            i++
        }
        return cartaPaso
    }

    fun pintar(c:Canvas){

        var pintar= Paint()

            c.drawBitmap(imagen,0f,150f,pintar)

    }
}