import pyautogui
import time
import keyboard
def Extractor(mode,player,invert):
    file = open("mc.txt",'r')
    txt = file.read()
    file.close()
    write = ""
    for i in range(0,len(txt.split("\n")) -1):

        tmp = txt.split("\n")[i]
        if(mode == 2 and tmp.split(' ')[2] == "BROKE"):
            continue
        if(mode == 3 and tmp.split(' ')[2] == "PLACED"):
            continue
        if(player != "" and tmp.split(' ')[1] != player):
            continue
        if(targetblock != "" and targetblock != tmp.split(' ')[3].lower()):
            continue
        print(i)
        block = ""
        if(invert == False):
            if(tmp.split(' ')[2] == "BROKE"):
                block = "air"
            else:
                if(targetblock == ""):
                    block = tmp.split(' ')[3].lower()
                else:
                    block = targetblock
        else:
            block = tmp.split(' ')[3].lower()
        if(i != 0):
            if(abs(float(txt.split("\n")[i-1].split(' ')[4].split('#')[0])-float(tmp.split(' ')[4].split('#')[0])) > 100 or abs(float(txt.split("\n")[i-1].split(' ')[4].split('#')[2])-float(tmp.split(' ')[4].split('#')[2]))):
                write = write + "/tp @p "  + tmp.split(' ')[4].split('#')[0] + " "+ tmp.split(' ')[4].split('#')[1]+ " "+ tmp.split(' ')[4].split('#')[2] + "\n"
        write = write + "/setblock " + tmp.split(' ')[4].split('#')[0] + " "+ tmp.split(' ')[4].split('#')[1]+ " "+ tmp.split(' ')[4].split('#')[2] + " minecraft:" + block.lower() + "\n"
    file1 = open("ispy.txt",'w')
    file1.write(write)
    file1.close()
def commands():
    file = open("ispy.txt",'r')
    txt = file.readlines()
    file.close()
    for i in range(0,len(txt)):
        pyautogui.press('t')
        pyautogui.write(txt[i].lower())
        pyautogui.press('enter')
        if(keyboard.is_pressed("esc")):
            break
    
mode = 0
player = ""
invert = False
targetblock = ""
print("place script in same folder as logs file and rename log file as 'mc' ")
while 0==0:
    a = input("1-all logs to commands | 2- only place commands | 3-only destroy commands |!4-write commands in minecraft from ispy file!")
    if(a == "1"):
        mode = 1
        break
    elif(a=="2"):
        mode = 2
        break
    elif(a=="3"):
        mode = 3
        break
    elif(a=="4"):
        mode = 4
        break
if(mode != 4):
    a = input("leve empty to track all players | type player name to track that player only")
    player = a
    if(mode == 1 or mode == 3):
        a = input("type anything to invert destroy (plock destroyed will be placed istead, useful when checking if someone has an xray) leave blank to disable")
        if(a != ""):
            invert = True
    a = input("target block | leave empty if all | NOTE: if invert is enabled and target block is selected, broken blocks will become target blocks")
    targetblock = a
    Extractor(mode,player,invert)
else:
    time.sleep(5)
    commands()