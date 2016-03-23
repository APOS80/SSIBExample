 (ns gui2.core
      (:require [seesaw.core]
                [seesaw.graphics]
                [seesaw.color])
      (:gen-class))
     
(seesaw.core/native!)

(def image (seesaw.graphics/buffered-image 400 400))

(def pic  (javax.imageio.ImageIO/read (java.io.File. (str  (System/getProperty "user.dir") "\\resources\\grumpy.jpg"))))

(def myfont (java.awt.Font. "TimesRoman" java.awt.Font/BOLD 20))

(def dr (let [g (.getGraphics image)
              t (.getTransform g)]                ;Save transformation
                 (.setColor g (seesaw.color/to-color :grey))
                 (.fillRect g 0 0 400 400)

                 (.setColor g (seesaw.color/to-color :blue))
                 (.drawRect g 10 10 50 50)
                 (.fillRect g 60 60 50 50)

                 (.translate g 300 300)           ;move origo
                 (.rotate g (/ (Math/PI) 4))      ;rotate 
                 (.drawImage g pic -100 -100 nil) ;draw image
                 (.setTransform g t)              ;Reset tranformation
                 
                 (.setColor g (seesaw.color/to-color :red))
                 (.setFont g myfont)
                 (.drawString g "This is RED" 10 20)
                 
                 (.setColor g (seesaw.color/to-color :green))
                 (.drawOval g 0 100 200 50)

                 (.rotate g (/ (Math/PI) 8))  ;Rotate, rotates around origo.
                 (.fillOval g 100 150 200 50)
                 (.setTransform g t)          ;Reset tranformation

                 (.setColor g (seesaw.color/to-color :black))
                 (.setStroke g (seesaw.graphics/stroke :width 3
                                                       :cap :round
                                                       :join :round  
                                                       :dashes [10,10,2,10]
                                                       :dash-phase 55555 
                                                       ))

                 (.drawLine g 0 0 200 200) 
                 (.drawPolyline g (int-array [100 300 300 100]) (int-array [100 100 350 350]) 4)

                 (seesaw.graphics/push g)))


(def gui (seesaw.core/frame
      :on-close :exit
      :title "Bogus!"
      :width 450
      :height 450
      :content (seesaw.core/label :id :tag2 :icon image)))
     
(defn -main [& args]
(seesaw.core/show! gui))
