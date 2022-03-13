from tkinter import *
from tkinter import filedialog
from tkinter.messagebox import *
import subprocess
import os
import platform
import npm_libraries
import templates
import com_tests_templates

# работа с файлами

def writeInFile(dir,content):
    f = open(dir,'w')
    f.write(content)
    f.close()

def writeRequestFuncModule(dir):
    writeInFile(dir,templates.request_func_template)

def writeUrlFile(dir):
    writeInFile(dir,templates.url_template)

def writeSelectorsModule(dir):
    writeInFile(dir, templates.selectors_templates)

def writeCreateBrowserModule(dir):
    writeInFile(dir,templates.createbrowser_template)

def writeUsersModule(dir):
    writeInFile(dir,templates.users_template)

def writeBabelrc(dir):
    writeInFile(dir,templates.babelrc_template)

def writeGitignore(dir):
    writeInFile(dir,templates.gitignore_template)

def writePageObjectBaseModules(dir):
    writeInFile(dir + "/BasePage.js", templates.basepage_template)
    writeInFile(dir + "/HomePage.js", templates.homepage_template)

def writeUITestExampleModule(dir):
    writeInFile(dir + "/test/e2e/example.test.js", templates.ui_test_example_template)

def writeAPITestExampleModule(dir):
    writeInFile(dir + "/test/api/example.test.js", templates.api_test_example_template)

# добавлять тесты общего назначения тут ->
def writeCommonTests(dir):
    writeInFile(dir + "/test/common_tests/service/PageLinks.js", com_tests_templates.page_link_class)
    writeInFile(dir + "/test/common_tests/link.test.js", com_tests_templates.bad_links_test_template)


#  создание файлов и папок

def e2eModulsCreate(dir):
    subprocess.run(["touch",
        dir+"/test/e2e/service/urls.js",
        dir+"/test/e2e/service/selectors.js",
        dir+"/test/e2e/service/users.js"])
    writeUrlFile(dir + "/test/e2e/service/urls.js")
    writeUsersModule(dir + "/test/e2e/service/users.js")
    writeSelectorsModule(dir + "/test/e2e/service/selectors.js")
    writeCreateBrowserModule(dir + "/test/e2e/service/createBrowser.js")
    writePageObjectBaseModules(dir + "/test/e2e/pages")

def apiModulsCreate(dir):
    subprocess.run(["touch",
        dir+"/test/api/service/urls.js"])
    writeRequestFuncModule(dir + "/test/api/service/RequestFunctions.js")

def commonModulsCreate(dir):
    subprocess.run(["touch",
        dir+"/test/common_tests/service/urls.js"])
    writeUrlFile(dir + "/test/common_tests/service/urls.js")

def baseCreate(dir):
    subprocess.run(["mkdir", 
        dir,
        dir+"/test"])
    writeBabelrc(dir+"/test/.babelrc")
    writeGitignore(dir+"/test/.gitignore")

def e2eCreate(dir):
    subprocess.run(["mkdir",
        dir+"/test/e2e/",
        dir+"/test/e2e/pages",
        dir+"/test/e2e/service"])

def apiCreate(dir):
    subprocess.run(["mkdir",
        dir+"/test/api/",
        dir+"/test/api/service"])

def commonCreate(dir):
    subprocess.run(["mkdir",
        dir+"/test/common_tests/",
        dir+"/test/common_tests/service"])


def npmUICreate(dir):
    # if "Windows" in platform.platform():
        # subprocess.run("cd "+dir, shell=True)
        subprocess.run("cd "+dir+" && npm init -yes", shell=True)
        for key in npm_libraries.ui_libraries:
            subprocess.run("cd "+dir+ " && npm install "
                +npm_libraries.ui_libraries.get(key), shell=True)

def npmApiCreate(dir):
    # if "Windows" in platform.platform():
        # subprocess.run("cd "+dir, shell=True)
        subprocess.run("cd "+dir+" && npm init -yes", shell=True)
        for key in npm_libraries.api_libraries:
            subprocess.run("cd "+dir+ " && npm install "
                +npm_libraries.api_libraries.get(key), shell=True)
            

# Создание фреймворка, основная логика
def createFolder():
    if len(entryName.get())!=0:
        if len(entrydirName.get())!=0:
            PROJKT_NAME=entryName.get()
            DIR_NAME=entrydirName.get()
            FULL_DIR_NAME=DIR_NAME+"/"+PROJKT_NAME

            if not os.path.exists(FULL_DIR_NAME):

                if (UI_var.get()==0) & (API_var.get()==0) & (COMMON_var.get()==0):
                    showinfo("Ошибка!", "Выбирите необходимый вид автотестов!")

                if (UI_var.get()==1) & (Temp_var.get()==1):
                    if not os.path.isdir(FULL_DIR_NAME):
                        baseCreate(FULL_DIR_NAME)
                    e2eCreate(FULL_DIR_NAME)
                    e2eModulsCreate(FULL_DIR_NAME)
                    writeUITestExampleModule(FULL_DIR_NAME)
                    showinfo("Создание шаблона автотеста.","Шаблон UI автотеста создан!")
                elif (UI_var.get()==1) & (Temp_var.get()==0):
                    if not os.path.isdir(FULL_DIR_NAME):
                        baseCreate(FULL_DIR_NAME)
                    e2eCreate(FULL_DIR_NAME)
                    e2eModulsCreate(FULL_DIR_NAME)
                # elif (UI_var.get()==0) & (Temp_var.get()==1):
                #     showinfo("Ошибка!", "Выбирите создание UI тестов!")

                if (API_var.get()==1) & (Temp_var.get()==1):
                    if not os.path.isdir(FULL_DIR_NAME):
                        baseCreate(FULL_DIR_NAME)
                    apiCreate(FULL_DIR_NAME)
                    apiModulsCreate(FULL_DIR_NAME)
                    writeAPITestExampleModule(FULL_DIR_NAME)
                    showinfo("Создание шаблона автотеста.","Шаблон API автотеста создан!")
                elif (API_var.get()==1) & (Temp_var.get()==0):
                    if not os.path.isdir(FULL_DIR_NAME):
                        baseCreate(FULL_DIR_NAME)
                    apiCreate(FULL_DIR_NAME)
                    apiModulsCreate(FULL_DIR_NAME)
                # elif (API_var.get()==0) & (Temp_var.get()==1):
                #     showinfo("Ошибка!", "Выбирите создание API тестов!")

                if (COMMON_var.get()==1):
                    if not os.path.isdir(FULL_DIR_NAME):
                        baseCreate(FULL_DIR_NAME)
                    commonCreate(FULL_DIR_NAME)
                    commonModulsCreate(FULL_DIR_NAME)
                    writeCommonTests(FULL_DIR_NAME)

                if (NPM_var.get()==1) & (UI_var.get()==1):
                    npmUICreate(FULL_DIR_NAME)
                
                if (NPM_var.get()==1) & (API_var.get()==1):
                    npmAPICreate(FULL_DIR_NAME)

                if os.path.isdir(FULL_DIR_NAME):
                    openFolder(FULL_DIR_NAME)

            else:
                showinfo("Ошибка!", "Директория с таким именем существует!")
                entryName.insert(0,"")
                entryName.focus()
            

        else:
            showinfo("Ошибка!", "Выбирите директорию!")
            entrydirName.focus()
    else:
        showinfo("Ошибка!", "Введите название проекта!")
        entryName.focus()

# дополнительные функции

def openFolder(dir):
    # if "Windows" in platform.platform():
        subprocess.run(['explorer', os.path.realpath(dir)])
        # os.system("explorer "+dir)
    
def get_npm_ver():
    # if "Windows" in platform.platform():
        npm_ver=subprocess.run("npm -v",stdout=subprocess.PIPE, text=True, shell=True)
        return npm_ver.stdout
    

def get_node_ver():
    # if "Windows" in platform.platform():
        node_ver=subprocess.run("node -v",stdout=subprocess.PIPE, text=True, shell=True)
        return node_ver.stdout

def getThisDir():
    # if "Windows" in platform.platform():
        # this_dir=subprocess.run("pwd",stdout=subprocess.PIPE, text=True, shell=True)
        # this_dir.stdout 
        full_dir = os.getcwd()
        if ("\\" in full_dir):
            full_dir_folders = full_dir.split("\\")
        elif ('/' in full_dir):
            full_dir_folders = full_dir.split("/")
        i = 0
        result_dir = ""
        for folder in full_dir_folders:
            i=i+1
            result_dir = result_dir + folder
            result_dir = result_dir +"\\"
            if (i%5==0):
                result_dir = result_dir + "\n"
        return result_dir


def insertDirName():
    entrydirName.insert(0, filedialog.askdirectory())



def exit():
    if askyesno("Выход", "Выйти из программы?"):
        root.destroy()

def showLibsList():
    all_libs=[]
    answ=""
    for key in npm_libraries.all_libraries:
            all_libs.append( npm_libraries.all_libraries.get(key))
    j=0
    for i in all_libs:
        j=j+1
        answ = answ + i + ",  "
        if (j%3==0):
            answ = answ+"\n"
    return answ

# код графического интерфейса

root =Tk()
UI_var=IntVar()
API_var=IntVar()
Temp_var=IntVar()
NPM_var=IntVar()
COMMON_var=IntVar()
root.title("TestAutomationFrameworkCreate TAFC")
x = (root.winfo_screenwidth() - root.winfo_reqwidth()) / 2
y = (root.winfo_screenheight() - root.winfo_reqheight()) / 2
x=x-200
y=y-200
root.wm_geometry("+%d+%d" % (x, y))
root.geometry("680x500")
# root.iconbitmap('./testAutoFramework.ico')


name = Label(root,text="Введите имя проекта:")
entryName = Entry(root,width=50)
name.grid(column=0,row=0,sticky=W,ipadx=10)
entryName.grid(column=1,row=0,pady=10,sticky=W)


dirName = Label(root,text="Укажите директорию:")
dirName.grid(column=0,row=1,sticky=W,ipadx=10)
entrydirName = Entry(root,width=50)
entrydirName.grid(column=1,row=1,pady=10,sticky=W)
dirName_btn = Button(root, text="Выбрать", font=("Arial Bold",8), command=insertDirName)
dirName_btn.grid(column=1, row=1,sticky=E)

testChk= Label(root,text="Виды тестов для проекта:")
chkUI = Checkbutton(root, text="UI тесты", variable=UI_var)
chkApi = Checkbutton(root, text="API тесты", variable=API_var)
chkCOMMON = Checkbutton(root, text="Тесты общего\n назначения", variable=COMMON_var)
testChk.grid(column=0,row=2,sticky=W,ipadx=10)
chkUI.grid(column=1,row=2,sticky=W+E)
chkApi.grid(column=1,row=2, sticky=W)
chkCOMMON.grid(column=1,row=2, sticky=E)
UI_var.set(1)

templatesUi = Label(root,text="Создать шаблон?")
chkTempUi = Checkbutton(root, text="Yes/No", variable=Temp_var)
templatesUi.grid(column=0,row=4, pady=5,sticky=W,ipadx=10)
chkTempUi.grid(column=1,row=4,sticky=W)

pkgjsnlb = Label(root, text="Создание npm проекта:")
chkpkgjsn = Checkbutton(root, text="Yes/No", variable=NPM_var)
pkgjsnlb.grid(column=0,row=5, pady=5,sticky=W,ipadx=10)
chkpkgjsn.grid(column=1,row=5,sticky=W)

allLibslb = Label(root, text="Список модулей:")
allLibslb.grid(column=0, row=6,sticky=W+N,ipadx=10)
if  len(showLibsList())==0:
    all_libs = Label(root,text="Ошибка!Проверьте файл со списком модулей!",fg="red")
else:
    all_libs = Label(root,text=showLibsList(),justify=LEFT)
all_libs.grid(column=1,row=6,sticky=W+N)


btnCreate = Button(root, text="  Создать  ", font=("Arial Bold",11),command=createFolder) # создание фреймворка
btnCreate.grid(column=2, row=8,sticky=S)

if len(get_npm_ver())>10 & len(get_npm_ver())==0:
    npmlb =Label(root,text="Версия npm : Ошибка!",fg="red")
else:
    npmlb =Label(root,text="Версия npm: ")
    npmlb_var = Label(root,text=get_npm_ver())
npmlb.grid(column=0,row=9,sticky=W+N,ipadx=10)
npmlb_var.grid(column=1,row=9,sticky=W)

nodelb =Label(root,text="Версия node: ")
if len(get_node_ver())>10 & len(get_node_ver())==0:
    nodelb_var =Label(root,text="Ошибка!",fg="red")
else:
    nodelb_var =Label(root,text=get_node_ver())
nodelb.grid(column=0,row=10,sticky=W+N,ipadx=10)
nodelb_var.grid(column=1,row=10,sticky=W)

pltflb = Label(root,text="Платформа: ")
pltflb_var= Label(root,text=platform.platform())
pltflb.grid(column=0, row=11,sticky=W,ipadx=10)
pltflb_var.grid(column=1, row=11,sticky=W)

thisDirlb = Label(root,text="Текущая директория: ")
thisDirlb_var = Label(root,text=getThisDir(),justify=LEFT)
thisDirlb.grid(column=0, row=12, sticky=W+N,ipadx=10)
thisDirlb_var.grid(column=1, row=12, sticky=W)

exitBtn = Button(root, text="  Выход  ", font=("Arial Bold",11),command=exit)
exitBtn.grid(column=2,row=13,sticky=N+E)

infAuth = Label(root,text="Автор:", font=("Arial Bold",7),fg="green")
infAuth_var = Label(root, text="Никонов Владислав Алексеевич", font=("Arial Bold",7),fg="green")
infCom = Label(root,text="Компания:", font=("Arial Bold",7),fg="green")
infCom_var = Label(root, text="OOO \"Красная рамка\" - Разработка сайтов и сложных веб-интерфейсов.", font=("Arial Bold",7),fg="green")
infAuth.grid(column=0, row=13, sticky=E+S)
infAuth_var.grid(column=1, row=13, sticky=W+S)
infCom.grid(column=0, row=14, sticky=E+S)
infCom_var.grid(column=1, row=14, sticky=W+S)

root.mainloop()
