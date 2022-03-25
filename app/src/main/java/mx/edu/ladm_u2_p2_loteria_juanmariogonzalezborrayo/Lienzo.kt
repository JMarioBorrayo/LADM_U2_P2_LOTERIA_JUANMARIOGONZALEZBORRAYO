package mx.edu.ladm_u2_p2_loteria_juanmariogonzalezborrayo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Size
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

class Lienzo(este:MainActivity): View(este) {
    val este = este
    var estaPausado=false
    var barajeo=0
    var ejecutar=false
    var empieza = Hilo(this)
    var mensajecontador = 0
    var cartas = arrayOf<Cartas>( Cartas(this,R.drawable.carta1), Cartas(this,R.drawable.carta2),Cartas(this,R.drawable.carta3),Cartas(this,R.drawable.carta4),Cartas(this,R.drawable.carta5),
        Cartas(this,R.drawable.carta6),Cartas(this,R.drawable.carta7),Cartas(this,R.drawable.carta8),Cartas(this,R.drawable.carta9),Cartas(this,R.drawable.carta10),
        Cartas(this,R.drawable.carta11),Cartas(this,R.drawable.carta12),Cartas(this,R.drawable.carta13),Cartas(this,R.drawable.carta14),Cartas(this,R.drawable.carta15),
        Cartas(this,R.drawable.carta16),Cartas(this,R.drawable.carta17),Cartas(this,R.drawable.carta18),Cartas(this,R.drawable.carta19),Cartas(this,R.drawable.carta20),
        Cartas(this,R.drawable.carta21),Cartas(this,R.drawable.carta22),Cartas(this,R.drawable.carta23),Cartas(this,R.drawable.carta24),Cartas(this,R.drawable.carta25),Cartas(this,R.drawable.carta26),
        Cartas(this,R.drawable.carta27),Cartas(this,R.drawable.carta28),Cartas(this,R.drawable.carta29),Cartas(this,R.drawable.carta30),Cartas(this,R.drawable.carta31),Cartas(this,R.drawable.carta32),
        Cartas(this,R.drawable.carta33),Cartas(this,R.drawable.carta34),Cartas(this,R.drawable.carta35),Cartas(this,R.drawable.carta36),Cartas(this,R.drawable.carta37),Cartas(this,R.drawable.carta38),
        Cartas(this,R.drawable.carta39),Cartas(this,R.drawable.carta40),Cartas(this,R.drawable.carta41),Cartas(this,R.drawable.carta42),Cartas(this,R.drawable.carta43),Cartas(this,R.drawable.carta44),
        Cartas(this,R.drawable.carta45),Cartas(this,R.drawable.carta46),Cartas(this,R.drawable.carta47),Cartas(this,R.drawable.carta48),Cartas(this,R.drawable.carta49),Cartas(this,R.drawable.carta50),
        Cartas(this,R.drawable.carta51),Cartas(this,R.drawable.carta52),Cartas(this,R.drawable.carta53),Cartas(this,R.drawable.carta54));
    var paso = 0;
    var mensaje = ""
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        c.drawColor(Color.WHITE)
        val p = Paint()
        p.color = Color.CYAN
        c.drawRect(0f, 0f, 1100f, 150f, p)
        if(ejecutar==false) {
            if (barajeo==0) {
                cartas.shuffle()
                barajeo++
            }
            c.drawColor(Color.WHITE)

            p.color = Color.CYAN
            c.drawRect(0f, 0f, 1100f, 150f, p)
            p.color = Color.BLACK
            p.textSize = 70f
            c.drawText("Bienvenidos", 350f, 100f, p)
            cartas[0].pintar(c)
            p.color = Color.GREEN
            c.drawRect(0f, 1200f, 1100f, 1400f, p)
            p.color = Color.BLACK
            p.textSize = 70f
            c.drawText("Jugar", 430f, 1320f, p)
        }else {

            invalidate()
            cartas[paso].pintar(c)
            p.color = Color.BLACK
            p.textSize = 70f
            c.drawText("Carta numero: ${paso+1} de 54", 200f, 100f, p)

            p.color = Color.YELLOW
            c.drawRect(0f, 1200f, 1100f, 1400f, p)
            p.color = Color.BLACK

            if(!estaPausado) {
                p.textSize = 70f
                c.drawText("Suspender", 450f, 1330f, p)
                invalidate()
            }else{
                p.color = Color.GREEN
                c.drawRect(0f, 1200f, 1100f, 1400f, p)
                p.textSize = 70f
                p.color = Color.BLACK
                c.drawText("Reanudar", 400f, 1330f, p)
                invalidate()
            }
            if(mensajecontador==1) {
                p.color = Color.RED
                c.drawRect(0f, 400f, 1200f, 600f, p)
                p.color = Color.WHITE
                p.textSize = 70f
                c.drawText(mensaje, 40f, 500f, p)
            }

            p.color = Color.RED
            c.drawRect(0f, 1400f, 1200f, 1600f, p)
            p.color = Color.BLACK
            p.textSize = 70f
            c.drawText("Terminar Juego", 300f, 1530f, p)
            invalidate()

        }

        invalidate()

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        este.setTitle("")
        when(event.action){
            MotionEvent.ACTION_DOWN->{
                if(ejecutar==false) {
                    if (event.x >= 0f && event.x <= 1100f && event.y >= 1200f && event.y <= 1400f) {
                        este.setTitle("El juego Acaba de Emepzar")
                        if(ejecutar==false) {
                            ejecutar = true
                            empieza.start()
                        }else{
                            ejecutar = false
                        }
                    }
                }else{
                    if (event.x >= 0f && event.x <= 1100f && event.y >= 1200f && event.y <= 1400f) {
                        if(!estaPausado) {
                            este.setTitle("El juego Se Pauso")
                            estaPausado = true
                        }else{
                            este.setTitle("Juego Despausado")
                            estaPausado = false
                            mensajecontador=0
                        }


                    }
                }
                invalidate()



                    if ( event.x>=0f && event.x<=1100f && event.y>=1400f && event.y<=1600f){

                        rutina2doPlanoAsincrona()

                        ejecutar=true
                        estaPausado=false

                    }

            }

        }
        return  super.onTouchEvent(event)
        invalidate()

    }

    fun rutina2doPlanoAsincrona()= GlobalScope.launch {

            este.runOnUiThread{
                if(paso<=15) {
                    mensaje = "Aun no puede Haber un ganador"
                    este.setTitle("Juego Pausado")
                    estaPausado = true
                    mensajecontador=1
                }else{
                    este.setTitle("Comprobando Cartas Restantes")
                }
            }

        delay(3000L)
    }
}
class Hilo(l: Lienzo): Thread(){
    var l =l;

    override fun run() {
        super.run()

        while (l.ejecutar) {
            if(!l.estaPausado) {
                if (l.paso == 54) {
                    l.ejecutar = false
                }
                sleep(4000L)
                l.paso++
            }
        }
    }
}