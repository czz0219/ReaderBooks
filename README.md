配置用户名、邮箱
git config --global user.name "czz"
git config --global user.email "freedom_czz@outlook.com"
列出所有配置
git config --list

		Git 仓库(本地库、远程库)
git init				--初始化版本库
git add					--添加新增文件/内容到暂存区域
git commit -m "描述"		--是将本地修改过的文件提交到本地库中。
git pull				--从远程更新资源到本地 先pull 再push
git push				--是将本地库中的最新信息发送给远程库。
git status				--查看仓库状态


git reset HEAD demo1.txt	--版本回退
git checkout -- demo1.txt	--请出暂存内容,回滚完成

git log		--查看提交日志，找到提交ID
git reset --hard 5eeb44d10cf6d68eedc5f582b24a3bac9402f146 
git仓库回滚到版本ID为 git log查找到的某个版本,这种操作会将所有提交到本地库的内容都回滚.

清空本地以及git本地仓库的文件
git rm filename    --清空本地仓库和目录的文件
git commit -m "clean rm2"	--提交更改

		Git远程/本地仓库
1、创建ssh key
	(1)在GIT本地仓库目录下右键  git bash
	(2)git bash中生成公钥和私钥(目录默认 /c/Users/freed/.ssh/) 
		ssh-keygen -t rsa -C "1908388450@qq.com"
2、查看公钥
	cat /c/Users/freed/.ssh/id_rsa.pub
3、测试
	ssh -T git@github.com     --注意邮箱一定要和注册账号邮箱相同
4、 利用 + 在github上创建远程仓库
5、开始创建本地仓库
	(1)echo "# ReaderBooks" >> README.md	--创建资源
	(2)git init								--覆盖性地初始化本地仓库
	(3)git add README.md					--添加资源到本地目录树上
	(4)git commit -m "first commit"			--对本地仓库的更改提交生效
	(5)git remote add origin https://github.com/czz0219/ReaderBooks.git --关联远程和本地仓库
	(6)git push -u origin master -f			--强制将本地资源的更新提交到远程仓库 master分支
											--以后不必指定分支 git push 默认master分支
	常常使用  1、3、4、6连提交资源

				克隆远程仓库到本地
git clone git@github.com:czz0219/ReaderBooks.git 
/*注意不能在现有仓库目录克隆，即没 .git/,这样指挥克隆文件资源，
通过 git init,git add files, 
git commit -m "clone repository and no remote repository to link"
生成一个未关联远程仓库的完整本地仓库 ,git status 产看状态正常
*/

	git 标签
git tag			--查看标签
git tag v1.0.1	--创建标签
git tag -a name -m "comment" --指定提交标签的信息
git tag -d name	--删除标签
git push origin v1.0.0	--提交到远程仓库
	git 分支管理
切换到主分支 : git checkout 分支名-master
当前(主)分支与index-icons分支合并:   git merge origin/index-icons    
同步: git push

解除git init 控制 即，删库
rm -rf  .git/
git pull 远程主机 远程分支 :本地分支
git pull origin master:master
