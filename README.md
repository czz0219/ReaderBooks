�����û���������
git config --global user.name "czz"
git config --global user.email "freedom_czz@outlook.com"
�г���������
git config --list

		Git �ֿ�(���ؿ⡢Զ�̿�)
git init				--��ʼ���汾��
git add					--��������ļ�/���ݵ��ݴ�����
git commit -m "����"		--�ǽ������޸Ĺ����ļ��ύ�����ؿ��С�
git pull				--��Զ�̸�����Դ������ ��pull ��push
git push				--�ǽ����ؿ��е�������Ϣ���͸�Զ�̿⡣
git status				--�鿴�ֿ�״̬


git reset HEAD demo1.txt	--�汾����
git checkout -- demo1.txt	--����ݴ�����,�ع����

git log		--�鿴�ύ��־���ҵ��ύID
git reset --hard 5eeb44d10cf6d68eedc5f582b24a3bac9402f146 
git�ֿ�ع����汾IDΪ git log���ҵ���ĳ���汾,���ֲ����Ὣ�����ύ�����ؿ�����ݶ��ع�.

��ձ����Լ�git���زֿ���ļ�
git rm filename    --��ձ��زֿ��Ŀ¼���ļ�
git commit -m "clean rm2"	--�ύ����

		GitԶ��/���زֿ�
1������ssh key
	(1)��GIT���زֿ�Ŀ¼���Ҽ�  git bash
	(2)git bash�����ɹ�Կ��˽Կ(Ŀ¼Ĭ�� /c/Users/freed/.ssh/) 
		ssh-keygen -t rsa -C "1908388450@qq.com"
2���鿴��Կ
	cat /c/Users/freed/.ssh/id_rsa.pub
3������
	ssh -T git@github.com     --ע������һ��Ҫ��ע���˺�������ͬ
4�� ���� + ��github�ϴ���Զ�ֿ̲�
5����ʼ�������زֿ�
	(1)echo "# ReaderBooks" >> README.md	--������Դ
	(2)git init								--�����Եس�ʼ�����زֿ�
	(3)git add README.md					--�����Դ������Ŀ¼����
	(4)git commit -m "first commit"			--�Ա��زֿ�ĸ����ύ��Ч
	(5)git remote add origin https://github.com/czz0219/ReaderBooks.git --����Զ�̺ͱ��زֿ�
	(6)git push -u origin master -f			--ǿ�ƽ�������Դ�ĸ����ύ��Զ�ֿ̲� master��֧
											--�Ժ󲻱�ָ����֧ git push Ĭ��master��֧
	����ʹ��  1��3��4��6���ύ��Դ

				��¡Զ�ֿ̲⵽����
git clone git@github.com:czz0219/ReaderBooks.git 
/*ע�ⲻ�������вֿ�Ŀ¼��¡����û .git/,����ָ�ӿ�¡�ļ���Դ��
ͨ�� git init,git add files, 
git commit -m "clone repository and no remote repository to link"
����һ��δ����Զ�ֿ̲���������زֿ� ,git status ����״̬����
*/

	git ��ǩ
git tag			--�鿴��ǩ
git tag v1.0.1	--������ǩ
git tag -a name -m "comment" --ָ���ύ��ǩ����Ϣ
git tag -d name	--ɾ����ǩ
git push origin v1.0.0	--�ύ��Զ�ֿ̲�
	git ��֧����
�л�������֧ : git checkout ��֧��-master
��ǰ(��)��֧��index-icons��֧�ϲ�:   git merge origin/index-icons    
ͬ��: git push

���git init ���� ����ɾ��
rm -rf  .git/
git pull Զ������ Զ�̷�֧ :���ط�֧
git pull origin master:master
