FROM gitpod/workspace-full

USER gitpod

RUN bash -c '. /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 21.0.4-oracle && \
    sdk default java 21.0.4-oracle && \
    VERSION="20.17.0" && \
    source $HOME/.nvm/nvm.sh && nvm install $VERSION && \
    nvm use $VERSION && nvm alias default $VERSION'

