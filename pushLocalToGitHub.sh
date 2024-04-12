#!/bin/bash  
  
# 检查是否在一个git仓库中  
if [ ! -d ".git" ]; then  
    echo "当前目录不是一个git仓库，请确保你在一个git仓库中执行此脚本。"  
    exit 1  
fi  
  
# 添加所有更改到暂存区  
git add .  
  
# 提交更改，并提示用户输入更新信息（如果未提供-m参数）  
if [ "$#" -eq 0 ]; then  
    read -p "请输入提交信息: " commit_message  
    git commit -m "$commit_message"  
else  
    git commit -m "$1"  
fi  
  
# 推送到远程仓库的master分支  
git push origin master
